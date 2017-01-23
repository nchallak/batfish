package org.batfish.smt;

import org.batfish.datamodel.Interface;
import org.batfish.datamodel.collections.NodeInterfacePair;

/**
 * Created by ryanbeckett on 12/7/16.
 */
public class GraphEdge {

    private Interface _start;
    private Interface _end;
    private String _router;
    private String _peer;

    public GraphEdge(Interface start, Interface end, String router, String peer) {
        _start = start;
        _end = end;
        _router = router;
        _peer = peer;
    }

    public Interface getStart() {
        return _start;
    }

    public Interface getEnd() {
        return _end;
    }

    public String getRouter() {
        return _router;
    }

    public String getPeer() {
        return _peer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphEdge graphEdge = (GraphEdge) o;
        return _router.equals(graphEdge._router) &&
               _start.getName().equals(graphEdge._start.getName());
    }

    @Override
    public int hashCode() {
        int result = _start.getName().hashCode();
        result = 31 * result + _router.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return _router + "," + _start.getName() + " --> " +
                (_peer == null ? "_" : _peer) + "," +
                (_end == null ? "_" : _end.getName());
    }

}