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

import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;
import org.terasology.world.viewer.zones.Zone;

@RegisterWorldGenerator(id = "PrototypeGenerator", displayName = "Prototype generator")
public class ProtoGenerator extends BaseFacetedWorldGenerator {

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    @In
    private BlockManager blockManager;

    public ProtoGenerator(SimpleUri uri) {
        super(uri);
    }

    /**
     * Creates the world, with our added customizations.
     * @return the WorldBuilder
     */
    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .setSeaLevel(0)
                .addZone(new Zone(pos -> pos.y() < 110)
                        .addProvider(new ProtoSurfaceProvider())
                        .addProvider(new ProtoBiomeFacetProvider())
                        .addRasterizer(new ProtoRasterizer()))
                .addZone(new Zone(pos -> pos.z() % 20 == 0 && pos.y() == 120)
                        .addRasterizer(new WorldRasterizer() {
                            @Override
                            public void initialize() {}

                            @Override
                            public void generateChunk(CoreChunk chunk, Region chunkRegion) {
                                Block grass = blockManager.getBlock("Core:Grass");
                                for (int chunkX = 0; chunkX < chunk.getChunkSizeX(); chunkX++) {
                                    for (int chunkZ = 0; chunkZ < chunk.getChunkSizeZ(); chunkZ++) {
                                        for (int chunkY = 0; chunkY < chunk.getChunkSizeY(); chunkY++) {
                                            chunk.setBlock(chunkX, chunkY, chunkZ, grass);
                                        }
                                    }
                                }
                            }
                        }))
                ;
    }
}
