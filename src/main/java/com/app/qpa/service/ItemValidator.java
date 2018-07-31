package com.app.qpa.service;

import com.app.qpa.element.Item;

/**
 * 稽核项校验，可以实现多个
 *
 */
public abstract class ItemValidator implements Validator<Item>{

    public abstract boolean validate(Item item);

    @Override
    public Class<Item> getType() {
        return Item.class;
    }
}
