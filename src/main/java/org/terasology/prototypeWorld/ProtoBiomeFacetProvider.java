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

import org.terasology.math.geom.BaseVector2i;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

@Produces(ProtoBiomeFacet.class)
@Updates(@Facet(SurfaceHeightFacet.class))
public class ProtoBiomeFacetProvider implements FacetProvider {

    @Override
    public void setSeed(long seed) {
        ProtoPlains.PLAINS.setSeed(seed);
        ProtoMountains.MOUNTAINS.setSeed(seed);
    }

    @Override
    public void process(GeneratingRegion region) {
        //Create the facet
        Border3D border = region.getBorderForFacet(ProtoBiomeFacet.class);
        ProtoBiomeFacet biomeFacet = new ProtoBiomeFacet(region.getRegion(), border);

        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);

        for (BaseVector2i pos : biomeFacet.getWorldRegion().contents()) {
            //Set negative x positions to be plains, and positive x positions to be mountains
            ProtoBiome biome = pos.x() <= 0 ? ProtoPlains.PLAINS : ProtoMountains.MOUNTAINS;
            biomeFacet.setWorld(pos, biome);

            //Update the world height based on the biome
            float height = surfaceHeightFacet.getWorld(pos);
            surfaceHeightFacet.setWorld(pos, biome.getNewSurfaceHeight(pos, height));
        }
        //Apply the facet
        region.setRegionFacet(ProtoBiomeFacet.class, biomeFacet);
    }
}
