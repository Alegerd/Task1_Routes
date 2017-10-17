package com.company.Interfaces;

import java.util.EventListener;

public interface IListener extends EventListener {
    void onEvent(IEvent event);
}
