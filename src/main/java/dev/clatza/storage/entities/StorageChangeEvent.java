package dev.clatza.storage.entities;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StorageChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private String fullStoragePath = null;
    private String newStorageContent = null;

    public StorageChangeEvent(String path) {
        this.fullStoragePath = path;
    }

    public StorageChangeEvent(String path, String newData) {
        this.fullStoragePath = path;
        this.newStorageContent = newData;
    }

    public String getPath() {
        return this.fullStoragePath;
    }

    public String getData() {
        return this.newStorageContent;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
