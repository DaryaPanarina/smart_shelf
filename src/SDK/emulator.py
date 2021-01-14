from threading import Thread
import cv2
import os
import time
import requests
from http.server import HTTPServer, BaseHTTPRequestHandler
from http import HTTPStatus
import json
from jsonschema import validate
from base64 import b64encode


def log(message):
    print("\nSHELF LOG: %s" % message)


# _url = 'http://127.0.0.1/api/postImage'
_url = 'https://api.imgbb.com/1/upload'
_product = {
    'productName': 'milk',
    'currency': 899,
    'value': 99.99
}
_currency = {
    643: 'RUB',
    840: 'USD',
    978: 'EUR',
}
_schema = {
    "type": "object",
    "required": ["productName", "currency", "value"],
    "properties": {
        "productName": {"type": "string"},
        "currency": {"type": "integer"},
        "value": {"type": "number"},
    },
}


class Server(BaseHTTPRequestHandler):
    def _header(self):
        self.send_header('Content-type', 'application/json')
        # Allow requests from any origin, so CORS policies don't
        # prevent local development.
        self.send_header('Access-Control-Allow-Origin', '*')
        self.end_headers()

    def _ok_header(self):
        self.send_response(HTTPStatus.OK.value)
        self._header()

    def _deny_header(self, message):
        self.send_response(HTTPStatus.BAD_REQUEST.value)
        self._header()
        self.wfile.write(json.dumps(
            {'success': False, 'message': message}).encode('utf-8'))

    def do_GET(self):
        self._ok_header()
        self.wfile.write(json.dumps(_product).encode('utf-8'))

    def do_POST(self):
        length = int(self.headers.get('content-length'))

        try:
            message = json.loads(self.rfile.read(length))
            try:
                validate(instance=message, schema=_schema)

                try:
                    currency = _currency[message['currency']]

                    _product['productName'] = message['productName']
                    _product['currency'] = message['currency']
                    _product['value'] = message['value']

                    self._ok_header()
                    self.wfile.write(json.dumps(
                        {'success': True}).encode('utf-8'))

                    log("set product to the shelf: %s - %d %s" %
                        (_product['productName'], _product['value'], currency))
                except KeyError:
                    self._deny_header("invalid currency value")
            except Exception as ex:
                self._deny_header(ex.message)
        except json.decoder.JSONDecodeError:
            self._deny_header("invalid json")

    def do_OPTIONS(self):
        # Send allow-origin header for preflight POST XHRs.
        self.send_response(HTTPStatus.NO_CONTENT.value)
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, POST')
        self.send_header('Access-Control-Allow-Headers', 'content-type')
        self.end_headers()


class Camera(object):
    def __init__(self, path):
        self.path = path
        self.cam = cv2.VideoCapture(path)

        fps = self.cam.get(cv2.CAP_PROP_FPS)
        frame_count = int(self.cam.get(cv2.CAP_PROP_FRAME_COUNT))
        # video duration time in mseconds
        self.duration = int(frame_count/fps * 1000)
        log("Load video %s duration %d " % (self.path, self.duration))

    def getFrame(self, msec):
        self.cam.set(cv2.CAP_PROP_POS_MSEC, msec % self.duration)
        ret, frame = self.cam.read()

        return frame


def run_server():
    server_address = ('localhost', 8080)
    httpd = HTTPServer(server_address, Server)
    log('serving at %s:%d' % server_address)
    httpd.serve_forever()


def run_camera():
    camera = Camera("video.mp4")
    secs = 0  # seconds from start of video

    try:
        while(True):
            time.sleep(2)
            frame = camera.getFrame(secs * 1000)
            name = './frame%d.jpg' % secs
            cv2.imwrite(name, frame)

            with open(name, 'rb') as file:
                payload = {
                    'key': 'fc8c7c9dfc77cadb0d47265cc1bd8382',
                    'image': b64encode(file.read()),
                }
                r = requests.post(_url, payload)

            log('Send file %s' % name)
            os.remove(name)
            secs += 2

    except Exception:
        camera.cam.release()
        cv2.destroyAllWindows()


thread1 = Thread(target=run_camera)
thread1.daemon = True
thread1.start()

run_server()
