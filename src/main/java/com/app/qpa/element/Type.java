package com.app.qpa.element;

import java.util.List;

/**
 * 稽核类型
 *
 */
public interface Type extends QpaModal{

	List<? extends Item> getItems();
}
