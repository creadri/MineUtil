package com.creadri.util;

import java.io.Serializable;
import org.bukkit.Location;

/**
 *
 * @author creadri
 */
public class Position implements Comparable<Position>, Serializable {
    int x;
    int y;
    int z;
    String world;

    public Position(int x, int y, int z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public Position() {
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
    
    public int compareTo(Location location) {
        return (this.x - location.getBlockX()) + (this.y - location.getBlockY()) + (this.z - location.getBlockZ()) + this.world.compareTo(location.getWorld().getName());
    }
    
    public int compareTo(int x, int y, int z, String world) {
        return (this.x - x) + (this.y - y) + (this.z - z) + this.world.compareTo(world);
    }

    @Override
    public int compareTo(Position o) {
        return (this.x - o.x) + (this.y - o.y) + (this.z - o.z) + this.world.compareTo(world);
    }
    
    public boolean equals(int x, int y, int z, String world) {
        return this.x == x && this.y == y && this.z == z && this.world.compareTo(world) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position p = (Position) obj;
        
        return x == p.x && y == p.y && z == p.z && world.compareTo(p.world) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        hash = 53 * hash + this.z;
        hash = 53 * hash + (this.world != null ? this.world.hashCode() : 0);
        return hash;
    }
}
