/*
 * Copyright 2010-2012 Luca Garulli (l.garulli(at)orientechnologies.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orientechnologies.orient.core.db.record.ridset.sbtree;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.orientechnologies.common.profiler.OProfilerMBean;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.index.sbtree.local.OSBTreeException;
import com.orientechnologies.orient.core.storage.impl.local.OStorageLocalAbstract;

/**
 * Persistent Set<OIdentifiable> implementation that uses the SBTree to handle entries in persistent way.
 * 
 * @author <a href="mailto:enisher@gmail.com">Artem Orobets</a>
 */
public class OIndexRIDContainer implements Set<OIdentifiable> {
  public static final String            INDEX_FILE_EXTENSION = ".irs";

  private final long                    fileId;
  private Set<OIdentifiable>            underlying;

  protected static final OProfilerMBean PROFILER             = Orient.instance().getProfiler();

  public OIndexRIDContainer(String name) {
    final OStorageLocalAbstract storage = (OStorageLocalAbstract) ODatabaseRecordThreadLocal.INSTANCE.get().getStorage()
        .getUnderlying();
    try {
      fileId = storage.getDiskCache().openFile(name + INDEX_FILE_EXTENSION);
    } catch (IOException e) {
      throw new OSBTreeException("Error creation of sbtree with name" + name, e);
    }
    underlying = new OIndexRIDContainerEmbedded();
  }

  public OIndexRIDContainer(long fileId, Set<OIdentifiable> underlying) {
    this.fileId = fileId;
    this.underlying = underlying;
  }

  public long getFileId() {
    return fileId;
  }

  @Override
  public int size() {
    return underlying.size();
  }

  @Override
  public boolean isEmpty() {
    return underlying.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return underlying.contains(o);
  }

  @Override
  public Iterator<OIdentifiable> iterator() {
    return underlying.iterator();
  }

  @Override
  public Object[] toArray() {
    return underlying.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return underlying.toArray(a);
  }

  @Override
  public boolean add(OIdentifiable oIdentifiable) {
    return underlying.add(oIdentifiable);
  }

  @Override
  public boolean remove(Object o) {
    return underlying.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return underlying.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends OIdentifiable> c) {
    return underlying.addAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return underlying.retainAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return underlying.removeAll(c);
  }

  @Override
  public void clear() {
    underlying.clear();
  }

  public boolean isEmbedded() {
    return underlying instanceof OIndexRIDContainerEmbedded;
  }

  public Set<OIdentifiable> getUnderlying() {
    return underlying;
  }
}
