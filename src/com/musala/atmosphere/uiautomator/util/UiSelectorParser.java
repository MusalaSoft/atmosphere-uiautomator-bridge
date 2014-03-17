package com.musala.atmosphere.uiautomator.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;

/**
 * Utility class responsible for converting {@link UiElementDescriptor} to string and this same string to a
 * {@link UiSelector} instance.
 * 
 * @author georgi.gaydarov
 * 
 */
public class UiSelectorParser {
    /**
     * Encodes a {@link UiElementDescriptor} instance in Base64 for further decoding using the
     * {@link #getSelectorFromRepresentation(String)} method.
     * 
     * @param descriptor
     *        - the {@link UiElementDescriptor} instance to be encoded.
     * @return the encoded descriptor <b>or null if encoding fails</b>.
     */
    public static String getStringRepresentation(UiElementDescriptor descriptor) {
        try {
            return toString(descriptor);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns a {@link UiSelector} instance based on an encoded in Base64 {@link UiElementDescriptor} instance.
     * 
     * @param descriptor
     *        - the encoded {@link UiElementDescriptor}.
     * @return the {@link UiSelector} instance, based on the descriptor <b>or null if decoding fails</b>.
     */
    public static UiSelector getSelectorFromRepresentation(String descriptor) {
        UiElementDescriptor descriptorReady = null;
        try {
            descriptorReady = (UiElementDescriptor) fromString(descriptor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return convertSelector(descriptorReady);
    }

    private static String toString(Serializable obj) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(output);
        objectStream.writeObject(obj);
        objectStream.close();
        byte[] result = output.toByteArray();
        return Base64Service.encode(result);
    }

    private static Object fromString(String representation) throws IOException, ClassNotFoundException {
        byte[] data = Base64Service.decode(representation);
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        Object result = inputStream.readObject();
        inputStream.close();
        return result;
    }

    private static UiSelector convertSelector(UiElementDescriptor descriptor) {
        UiSelector selector = new UiSelector();
        Boolean checked = descriptor.isChecked();
        String className = descriptor.getClassName();
        Boolean clickable = descriptor.isClickable();
        String description = descriptor.getContentDescription();
        Boolean enabled = descriptor.isEnabled();
        Boolean focusable = descriptor.isFocusable();
        Boolean focused = descriptor.isFocused();
        Integer index = descriptor.getIndex();
        Boolean longClickable = descriptor.isLongClickable();
        String packageName = descriptor.getPackageName();
        Boolean scrollable = descriptor.isScrollable();
        Boolean selected = descriptor.isSelected();
        String text = descriptor.getText();

        if (checked != null) {
            selector.checked(checked);
        }
        if (className != null) {
            selector.className(className);
        }
        if (clickable != null) {
            selector.clickable(clickable);
        }
        if (description != null) {
            selector.description(description);
        }
        if (enabled != null) {
            selector.enabled(enabled);
        }
        if (focusable != null) {
            selector.focused(focusable);
        }
        if (focused != null) {
            selector.focused(focused);
        }
        if (index != null) {
            selector.index(index);
        }
        if (longClickable != null) {
            selector.longClickable(longClickable);
        }
        if (packageName != null) {
            selector.packageName(packageName);
        }
        if (scrollable != null) {
            selector.scrollable(scrollable);
        }
        if (selected != null) {
            selector.selected(selected);
        }
        if (text != null) {
            selector.text(text);
        }
        return selector;
    }
}
