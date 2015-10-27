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

import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 同步事件发布器
 * 
 * @author guojf
 * @version 1.0.0.2011-12-26
 */
public class SyncApplicationEventPublisher extends AbstractApplicationEventPublisher {

    private Executor executor = Executors.newSingleThreadExecutor();

    /**
     * {@inheritDoc}
     * 
     * @see com.dinstone.practice.event.ApplicationEventPublisher#publishEvent(com.dinstone.practice.event.ApplicationEvent)
     */
    public void publishEvent(final ApplicationEvent event) {
        for (Iterator<ApplicationListener> it = getApplicationListeners().iterator(); it.hasNext();) {
            final ApplicationListener listener = it.next();
            executor.execute(new Runnable() {

                public void run() {
                    listener.handleEvent(event);
                }
            });
        }
    }

}
