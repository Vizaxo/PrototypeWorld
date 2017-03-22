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

import org.terasology.math.Region3i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.facets.base.BaseObjectFacet2D;

public class ProtoBiomeFacet extends BaseObjectFacet2D<ProtoBiome> {

    ProtoBiome biome;
    ProtoBiomeRasterizer rasterizer;

    public ProtoBiomeFacet(Region3i targetRegion, Border3D border, ProtoBiome biome, ProtoBiomeRasterizer rasterizer) {
        super(targetRegion, border, ProtoBiome.class);
        rasterizer.initialize();
        this.biome = biome;
        this.rasterizer = rasterizer;
    }

    public ProtoBiome getBiome() {
        return biome;
    }

    public ProtoBiomeRasterizer getRasterizer() {
        return rasterizer;
    }
}
