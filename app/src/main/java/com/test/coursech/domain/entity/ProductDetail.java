package com.test.coursech.domain.entity;

public class ProductDetail {
    private String name;
    private int preview;
    private String price;
    private int countLastUser;
    private double appraisal;
    private String mainGender;
    private int[] activityByH;
    private int[] appraisalAll;

    public ProductDetail(
            String name,
            int preview,
            String price,
            int countLastUser,
            double appraisal,
            String mainGender,
            int[] activityByH,
            int[] appraisalAll
    ){
        this.name=name;
        this.preview=preview;
        this.price = price;
        this.countLastUser = countLastUser;
        this.appraisal = appraisal;
        this.mainGender = mainGender;
        this.activityByH = activityByH;
        this.appraisalAll = appraisalAll;
    }

    public String getName() {
        return this.name;
    }

    public int getPreview() {
        return this.preview;
    }

    public String getPrice() {
        return this.price;
    }

    public int getCountLastUser() {
        return this.countLastUser;
    }

    public double getAppraisal() {
        return this.appraisal;
    }

    public String getMainGender() {
        return this.mainGender;
    }

    public int[] getActivityByH() {
        return this.activityByH;
    }

    public int[] getAppraisalAll() {
        return this.appraisalAll;
    }

}