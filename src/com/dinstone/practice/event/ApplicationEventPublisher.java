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

/**
 * Interface that encapsulates event publication functionality.
 * 
 * @author guojf
 * @version 1.0.0.2011-12-26
 */
public interface ApplicationEventPublisher {

    /**
     * Add a listener to be notified of all events.
     * 
     * @param listener
     *        the listener to add
     */
    void addApplicationListener(ApplicationListener listener);

    /**
     * Remove a listener from the notification list.
     * 
     * @param listener
     *        the listener to remove
     */
    void removeApplicationListener(ApplicationListener listener);

    /**
     * Remove all listeners registered with this publisher. It will perform no
     * action on event notification until more listeners are registered.
     */
    void removeAllListeners();

    /**
     * Notify all listeners registered with this application of an application
     * event.
     * 
     * @param event
     *        the event to publish
     */
    void publishEvent(ApplicationEvent event);

}
