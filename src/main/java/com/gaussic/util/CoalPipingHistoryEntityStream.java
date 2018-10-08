package com.gaussic.util;

import com.gaussic.model.history.CoalPipingHistory;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.BaseStream;

public class CoalPipingHistoryEntityStream implements BaseStream<CoalPipingHistory,CoalPipingHistoryEntityStream>{
    @Override
    public Iterator<CoalPipingHistory> iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    public Spliterator<CoalPipingHistory> spliterator() {
        return null;
    }

    @Override
    public boolean isParallel() {
        return false;
    }

    @Override
    public CoalPipingHistoryEntityStream sequential() {
        return null;
    }

    @Override
    public CoalPipingHistoryEntityStream parallel() {
        return null;
    }

    @Override
    public CoalPipingHistoryEntityStream unordered() {
        return null;
    }

    @Override
    public CoalPipingHistoryEntityStream onClose(Runnable closeHandler) {
        return null;
    }

    @Override
    public void close() {

    }
}
