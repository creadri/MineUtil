package com.creadri.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author creadri
 */
public class PositionMap<T> implements Serializable{
    private HashMap<Position, T> map;
    private Position position;
    
    public PositionMap() {
       position = new Position();
       map = new HashMap<Position, T>();
    }
    
    public void put(int x, int y, int z, String world, T object) {
        Position pos = new Position(x, y, z, world);
        
        map.put(pos, object);
    }
    
    public void put(Position key, T object) {
        map.put(key, object);
    }
    
    public boolean containsPosition(Position key) {
        return map.containsKey(key);
    }
    
    public boolean containsPosition(int x, int y, int z, String world) {
        position.setX(x);
        position.setY(y);
        position.setZ(z);
        position.setWorld(world);
        
        return map.containsKey(position);
    }
    
    public T get(Position key) {
        return map.get(key);
    }
    
    public T get(int x, int y, int z, String world) {
        position.setX(x);
        position.setY(y);
        position.setZ(z);
        position.setWorld(world);
        
        return map.get(position);
    }
    
    public Collection<T> values() {
        return map.values();
    }
    
    public void remove(Position key) {
        map.remove(key);
    }
    
    public void remove(int x, int y, int z, String world) {
        position.setX(x);
        position.setY(y);
        position.setZ(z);
        position.setWorld(world);
        
        map.remove(position);
    }
    
    public int size() {
        return map.size();
    }
}
