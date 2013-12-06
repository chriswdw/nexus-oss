/**
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

package org.sonatype.nexus.analytics.rest

import org.apache.shiro.authz.annotation.RequiresPermissions
import org.sonatype.nexus.analytics.EventData
import org.sonatype.nexus.analytics.EventStore
import org.sonatype.sisu.goodies.common.ComponentSupport
import org.sonatype.sisu.siesta.common.Resource

import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import static com.google.common.base.Preconditions.checkNotNull

/**
 * Analytics events resource.
 *
 * @since 2.8
 */
@Named
@Singleton
@Path(EventsResource.RESOURCE_URI)
class EventsResource
    extends ComponentSupport
    implements Resource
{
  static final String RESOURCE_URI = '/analytics/events'

  private final EventStore eventStore

  @Inject
  EventsResource(final EventStore eventStore) {
    this.eventStore = checkNotNull(eventStore)
  }

  /**
   * List all events.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RequiresPermissions('nexus:analytics')
  List<EventData> list() {
    List<EventData> events = []
    for (EventData data : eventStore) {
      events << data
    }
    return events
  }

  /**
   * Clear all event data.
   */
  @DELETE
  @RequiresPermissions('nexus:analytics')
  void clear() {
    eventStore.clear()
  }

  /**
   * Append events.  This requires no permissions.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void append(List<EventData> events) {
    events.each {
      eventStore.add(it)
    }
  }
}