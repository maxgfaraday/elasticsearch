/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.snapshots;

import org.elasticsearch.cluster.metadata.SnapshotId;
import org.elasticsearch.rest.RestStatus;

/**
 * Thrown when a user tries to start multiple snapshots at the same time
 */
public class ConcurrentSnapshotExecutionException extends SnapshotException {
    public ConcurrentSnapshotExecutionException(SnapshotId snapshot, String msg) {
        super(snapshot, msg);
    }

    public ConcurrentSnapshotExecutionException(SnapshotId snapshot, String msg, Throwable cause) {
        super(snapshot, msg, cause);
    }

    @Override
    public RestStatus status() {
        return RestStatus.SERVICE_UNAVAILABLE;
    }
}
