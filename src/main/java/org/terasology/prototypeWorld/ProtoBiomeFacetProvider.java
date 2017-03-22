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

import org.terasology.entitySystem.Component;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.world.biomes.Biome;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

import static java.lang.Math.abs;

@Produces(ProtoBiomeFacet.class)
@Updates(@Facet(SurfaceHeightFacet.class))
public class ProtoBiomeFacetProvider implements FacetProvider {

    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(ProtoBiomeFacet.class);
        ProtoBiomeFacet biomeFacet = new ProtoBiomeFacet(region.getRegion(), border);

        for (BaseVector2i position : biomeFacet.getWorldRegion().contents()) {
            ProtoBiome biome = new ProtoBiome();
            biomeFacet.setWorld(position, biome);
        }
        region.setRegionFacet(ProtoBiomeFacet.class, biomeFacet);
    }
}
