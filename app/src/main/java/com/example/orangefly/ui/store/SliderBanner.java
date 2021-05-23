package com.example.orangefly.ui.store;

public class SliderBanner {
    // image url is used to
    // store the url of image
    private String imgUrl;
    private int image_id;

    // Constructor method.
    public SliderBanner(String imgUrl, int image_id) {
        this.imgUrl = imgUrl;
        this.image_id = image_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    // Getter method
    public String getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
