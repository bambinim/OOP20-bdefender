package com.bdefender.shop;

import javafx.scene.Parent;

public class ShopView extends Parent {
    private Parent shopParent;
    public void setView(final Parent view) {
        this.shopParent = view;
    }

    public final Parent getView() {
        return this.shopParent;
    }


}
