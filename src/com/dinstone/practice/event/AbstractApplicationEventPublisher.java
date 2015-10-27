/*
 * Copyright (C) 2011~2012 dinstone <dinstone@163.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.dinstone.practice.event;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Abstract implementation of the {@link ApplicationEventPublisher} interface,
 * providing the basic listener registration facility.
 * 
 * @author guojf
 * @version 1.0.0.2011-12-26
 */
public abstract class AbstractApplicationEventPublisher implements ApplicationEventPublisher {

    /** Collection of ApplicationListeners */
    private Collection<ApplicationListener> applicationListeners = new CopyOnWriteArraySet<ApplicationListener>();

    /**
     * {@inheritDoc}
     * 
     * @see com.dinstone.practice.event.ApplicationEventPublisher#addApplicationListener(com.dinstone.practice.event.ApplicationListener)
     */
    public void addApplicationListener(ApplicationListener listener) {
        this.applicationListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.dinstone.practice.event.ApplicationEventPublisher#removeApplicationListener(com.dinstone.practice.event.ApplicationListener)
     */
    public void removeApplicationListener(ApplicationListener listener) {
        this.applicationListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.dinstone.practice.event.ApplicationEventPublisher#removeAllListeners()
     */
    public void removeAllListeners() {
        this.applicationListeners.clear();
    }

    /**
     * the applicationListeners to get
     * 
     * @return the applicationListeners
     * @see AbstractApplicationEventPublisher#applicationListeners
     */
    protected Collection<ApplicationListener> getApplicationListeners() {
        return applicationListeners;
    }

}
