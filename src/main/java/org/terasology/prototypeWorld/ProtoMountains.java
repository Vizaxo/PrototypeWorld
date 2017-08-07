/*
 * Copyright 2017 MovingBlocks
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
package org.terasology.prototypeWorld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Vector2f;
import org.terasology.registry.CoreRegistry;
import org.terasology.rendering.nui.Color;
import org.terasology.utilities.procedural.BrownianNoise;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.PerlinNoise;
import org.terasology.utilities.procedural.SubSampledNoise;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class ProtoMountains extends ProtoBiome {
    public static final ProtoMountains MOUNTAINS = new ProtoMountains(Color.CYAN);

    private Block grass;
    private Block stone;
    private Noise mountainNoise;

    private ProtoMountains(Color previewColor) {
        super(previewColor);
    }

    @Override
    public void initialize() {
        BlockManager blockManager = CoreRegistry.get(BlockManager.class);
        grass = blockManager.getBlock("Core:Grass");
        stone = blockManager.getBlock("Core:Stone");
    }

    @Override
    public void setSeed(long seed) {
        //Add noise for the mountains
        mountainNoise = new SubSampledNoise(new BrownianNoise(new PerlinNoise(seed + 2), 8), new Vector2f(0.004f, 0.004f), 1);
    }

    @Override
    public String getId() {
        return "PrototypeWorld:Mountains";
    }

    @Override
    public String getName() {
        return "Mountains";
    }

    @Override
    public float getNewSurfaceHeight(BaseVector2i pos, float height) {
        //Add the mountains
        return height + Math.abs(mountainNoise.noise(pos.x(), pos.y()) * 100);
    }

    @Override
    public void generateColumn(CoreChunk chunk, Region chunkRegion, int worldX, int worldZ) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        float surfaceHeight = surfaceHeightFacet.getWorld(worldX, worldZ);

        //Set columns higher than 130 blocks to be stone, and lower columns to be grass
        boolean stoneColumn = false;
        for (int chunkY = chunk.getChunkSizeY() - 1; chunkY >= 0; chunkY--) {
            if (chunk.chunkToWorldPositionY(chunkY) < surfaceHeight) {
                if(chunk.chunkToWorldPositionY(chunkY) > 130) {
                    stoneColumn = true;
                }
                chunk.setBlock(ChunkMath.calcBlockPosX(worldX), chunkY, ChunkMath.calcBlockPosZ(worldZ), (stoneColumn ? stone : grass));
            }
        }
    }
}
