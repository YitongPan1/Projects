package diver;

import datastructures.PQueue;
import datastructures.SlowPQueue;
import game.*;
import graph.ShortestPaths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/** This is the place for your implementation of the {@code SewerDiver}.*/
public class McDiver implements SewerDiver {


    /** See {@code SewerDriver} for specification.*/
    @Override
    public void seek(SeekState state) {
        HashSet<Long> visit = new HashSet<>();
        seekfunction(state, visit);
    }

        // TODO : Look for the ring and return.
        // DO NOT WRITE ALL THE CODE HERE. DO NOT MAKE THIS METHOD RECURSIVE.
        // Instead, write your method (it may be recursive) elsewhere, with a
        // good specification, and call it from this one.
        //
        // Working this way provides you with flexibility. For example, write
        // one basic method, which always works. Then, make a method that is a
        // copy of the first one and try to optimize in that second one.
        // If you don't succeed, you can always use the first one.
        //
        // Use this same process on the second method, scram.

    /** recursive DFS seek visit every node reachable along paths of unvisited nodes from node u
     * end with walker standing on the ring position. */
    private void seekfunction (SeekState state, HashSet<Long> visit) {
        Long u = state.currentLocation();
        visit.add(u);
        PQueue<NodeStatus> neighbor = new SlowPQueue<>();
        for(NodeStatus w : state.neighbors()) {
            neighbor.add(w,w.getDistanceToRing());
        }
        while(!neighbor.isEmpty()) {
            NodeStatus w = neighbor.extractMin();
            if(!visit.contains(w.getId())) {
                if(w.getDistanceToRing() == 0) {
                    state.moveTo(w.getId());
                    return;
                }
                state.moveTo(w.getId());
                seekfunction(state,visit);
                if(state.distanceToRing() == 0) {
                    return;
                }
                state.moveTo(u);
            }
        }
    }

    /** See {@code SewerDriver} for specification. */
    @Override
    public void scram(ScramState state) {
        // TODO: Get out of the sewer system before the steps are used up.
        // DO NOT WRITE ALL THE CODE HERE. Instead, write your method elsewhere,
        // with a good specification, and call it from this one.
        app(state);
    }

    /** The shortest path for getting to the exit. */
    private void scramP(ScramState state) {
        Maze maze = new Maze((Set<Node>) state.allNodes());
        ShortestPaths<Node, Edge> dijkstra = new ShortestPaths<>(maze);
        dijkstra.singleSourceDistances(state.exit());
        List<Edge> edgeList = dijkstra.bestPath(state.currentNode());
        for (int i = edgeList.size() - 1; i >= 0; i--) {
            state.moveTo(edgeList.get(i).source());
        }
    }

    /** Traversal the map for picking up more coins before steps got used up. */
    private void app(ScramState state) {
        Maze maze = new Maze((Set<Node>) state.allNodes());
        ShortestPaths<Node, Edge> exitD = new ShortestPaths<>(maze);
        ShortestPaths<Node, Edge> startD = new ShortestPaths<>(maze);
        exitD.singleSourceDistances(state.exit());
        startD.singleSourceDistances(state.currentNode());
        Map<Node, Double> value = new HashMap<>();
        for(Node i: state.allNodes()) {
            value.put(i, i.getTile().coins()/1.0);
        }
        while(!state.currentNode().equals(state.exit())) {
            while (!value.isEmpty()) {
                startD.singleSourceDistances(state.currentNode());
                for(Node i : value.keySet()) {
                    value.put(i, i.getTile().coins()/1.0);
                }
                Node currentMaxNode = findMax(value);
                value.remove(currentMaxNode);

                double sToM = startD.getDistance(currentMaxNode);
                double mToExit = exitD.getDistance(currentMaxNode);
                double totalPath = sToM+mToExit;
                if (totalPath < state.stepsToGo()) {
                    List<Edge> edgeList = startD.bestPath(currentMaxNode);
                    for(int i = 0; i < edgeList.size(); i++) {
                        state.moveTo(edgeList.get(i).destination());
                    }
                }
            }
            scramP(state);
        }
    }

    /** Find the node with the biggest coin value. */
    private Node findMax(Map<Node, Double> countMap) {
        Node out = null;
        int max = Integer.MIN_VALUE;
        for (Node i: countMap.keySet()) {
            if (countMap.get(i) >= max) {
                out = i;
            }
        }
        return out;
    }
}
