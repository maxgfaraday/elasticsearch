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

package org.elasticsearch.common.table;


import com.google.common.collect.Maps;
import org.elasticsearch.ElasticSearchIllegalArgumentException;
import org.elasticsearch.common.Table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class TimestampedTable extends Table {

    private Date now;

    public TimestampedTable () {
        super();
        this.now = new Date();
    }

    @Override
    public Table startHeaders() {
        inHeaders = true;
        currentCells = new ArrayList<Cell>();
        
        final Map<String, String> epochAttr = Maps.newHashMap();
        epochAttr.put("desc", "seconds since 1970-01-01 00:00:00 when request executed");
        
        final Map<String, String> hmsAttr = Maps.newHashMap();
        hmsAttr.put("desc", "human time when request executed");
        
        currentCells.add(new Cell("epoch", epochAttr));
        currentCells.add(new Cell("time", hmsAttr));
        return this;
    }

    @Override
    public Table startRow() {
        SimpleDateFormat dfHms = new SimpleDateFormat("HH:mm:ss", Locale.ROOT);

        if (headers.isEmpty()) {
            throw new ElasticSearchIllegalArgumentException("no headers added...");
        }
        currentCells = new ArrayList<Cell>(headers.size());
        currentCells.add(new Cell(now.getTime() / 1000));
        currentCells.add(new Cell(dfHms.format(now)));
        return this;
    }

}
