/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2013 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.analytics.internal;

import java.util.Iterator;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.analytics.EventData;
import org.sonatype.nexus.analytics.EventStore;
import org.sonatype.sisu.goodies.lifecycle.LifecycleSupport;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default {@link EventStore} implementation.
 *
 * @since 2.8
 */
@Named
@Singleton
public class EventStoreImpl
  extends LifecycleSupport
  implements EventStore
{
  // HACK: For now using in-memory just to move things forward
  private final List<EventData> storage = Lists.newLinkedList();

  @Override
  protected void doStart() throws Exception {
    // nop
  }

  @Override
  protected void doStop() throws Exception {
    // nop
  }

  @Override
  public void add(final EventData data) {
    checkNotNull(data);
    storage.add(data);
  }

  @Override
  public void clear() {
    storage.clear();
  }

  @Override
  public long size() {
    return storage.size();
  }

  @Override
  public Iterator<EventData> iterator() {
    return storage.iterator();
  }
}
