package me.gregorsomething.veryusefullmod.util;

import com.google.common.collect.Sets;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 * This is useless and doesn't work as intended
 */
public class LoacatorByNear {
    private BlockPos pos1;
    private BlockPos pos2;
    private BlockPos pos3;
    private int nearNr1;
    private int nearNr2;
    private int nearNr3;

    public void setPos1(BlockPos p, int n) {
        this.pos1 = p;
        this.nearNr1 = n;
    }
    public void setPos2(BlockPos p, int n) {
        this.pos2 = p;
        this.nearNr2 = n;
    }
    public void setPos3(BlockPos p, int n) {
        this.pos3 = p;
        this.nearNr3 = n;
    }

    public BlockPos calculate() {
        Sphere s1 = new Sphere(pos1, nearNr1);
        Sphere s2 = new Sphere(pos2, nearNr2);
        Sphere s3 = new Sphere(pos3, nearNr3);
        Set<BlockPos> result = this.intersection(s1.getAllPos(), s2.getAllPos(), s3.getAllPos());
        //return (BlockPos) result.toArray()[0];
        int X = 0, Y = 0, Z = 0;
        for (BlockPos pos: result) {
            X += pos.getX();
            Y += pos.getY();
            Z += pos.getZ();
        }
        return new BlockPos(Math.round(X/result.size()), Math.round(Y/result.size()), Math.round(Z/result.size()));
    }

    private class Sphere {
        public int x, y, z, r;
        public Sphere(BlockPos pos, int r) {
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.r = r;
        }

        public ArrayList<BlockPos> getAllPos() {
            ArrayList<BlockPos> set = new ArrayList<BlockPos>();
            for (int X = -1*r; X <= r; X++) {
                for (int Z = -1*r; Z <= r; Z++) {
                    for (int Y = -1*r; Y <= r; Y++) {
                        BlockPos tPos = new BlockPos(x+X,y+Y ,z+Z);
                        if (this.isIn(tPos)) set.add(tPos);
                    }
                }
            }
            return set;
        }

        public boolean isIn(BlockPos pos) {
            return (Math.sqrt((x-pos.getX())^2+(z-pos.getZ())^2+(y-pos.getY())^2) <= r);
        }
    }

    public Set<BlockPos> intersection(List<BlockPos>... list) {
        Set<BlockPos> result = Sets.newHashSet(list[0]);
        for (List<BlockPos> numbers : list) {
            result = Sets.intersection(result, Sets.newHashSet(numbers));
        }
        return result;
    }
}
