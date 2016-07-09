package com.pavelsikun.seekbarpreference;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Pavel Sikun on 21.05.16.
 */

public class SeekBarPreference extends Preference implements View.OnClickListener, PreferenceControllerDelegate.ViewStateListener, PersistValueListener, ChangeValueListener {

    private PreferenceControllerDelegate controllerDelegate;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SeekBarPreference(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        setLayoutResource(R.layout.seekbar_view_layout);
        controllerDelegate = new PreferenceControllerDelegate(getContext(), false);

        controllerDelegate.setViewStateListener(this);
        controllerDelegate.setPersistValueListener(this);
        controllerDelegate.setChangeValueListener(this);

        controllerDelegate.loadValuesFromXml(attrs);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        controllerDelegate.onBind(view);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        controllerDelegate.setCurrentValue(getPersistedFloat(controllerDelegate.getCurrentValue()));
    }

    @Override
    public boolean persistInt(int value) {
        return super.persistInt(value);
    }

    @Override
    public boolean persistFloat(float value) {
        return super.persistFloat(value);
    }

    @Override
    public boolean onChange(int value) {
        return callChangeListener(value);
    }

    @Override
    public boolean onChange(float value) {
        return callChangeListener(value);
    }

    @Override
    public void onClick(final View v) {
        controllerDelegate.onClick(v);
    }

    public float getMaxValue() {
        return controllerDelegate.getMaxValue();
    }

    public void setMaxValue(float maxValue) {
        controllerDelegate.setMaxValue(maxValue);
    }

    public float getMinValue() {
        return controllerDelegate.getMinValue();
    }

    public void setMinValue(float minValue) {
        controllerDelegate.setMinValue(minValue);
    }

    public float getInterval() {
        return controllerDelegate.getInterval();
    }

    public void setInterval(float interval) {
        controllerDelegate.setInterval(interval);
    }

    public float getCurrentValue() {
        return controllerDelegate.getCurrentValue();
    }

    public void setCurrentValue(int currentValue) {
        controllerDelegate.setCurrentValue(currentValue);
        persistFloat(controllerDelegate.getCurrentValue());
    }

    public String getMeasurementUnit() {
        return controllerDelegate.getMeasurementUnit();
    }

    public void setMeasurementUnit(String measurementUnit) {
        controllerDelegate.setMeasurementUnit(measurementUnit);
    }

    public boolean isDialogEnabled() {
        return controllerDelegate.isDialogEnabled();
    }

    public void setDialogEnabled(boolean dialogEnabled) {
        controllerDelegate.setDialogEnabled(dialogEnabled);
    }

    public void setDialogStyle(int dialogStyle) {
        controllerDelegate.setDialogStyle(dialogStyle);
    }
}
