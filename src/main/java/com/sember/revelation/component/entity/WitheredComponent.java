package com.sember.revelation.component.entity;

import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class WitheredComponent implements BooleanComponent, AutoSyncedComponent {

    private boolean withered;

    @Override
    public boolean getValue() {
        return withered;
    }

    @Override
    public void setValue(boolean value) {
        this.withered = value;
    }

    @Override
    public void toggle() {
        this.withered = !this.withered;
    }

    @Override
    public void readData(ReadView readView) {
        this.withered = readView.getBoolean("withered", false);
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putBoolean("withered", this.withered);
    }

}
