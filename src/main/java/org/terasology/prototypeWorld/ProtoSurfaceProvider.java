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
import org.terasology.math.geom.Rect2i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

@Produces(SurfaceHeightFacet.class)
public class ProtoSurfaceProvider implements FacetProvider {

    /**
     * Calculates the basic heightmap for the world.
     * @param region the region we are generating
     */
    public void process(GeneratingRegion region) {
        //Create the facet
        Border3D border = region.getBorderForFacet(SurfaceHeightFacet.class);
        SurfaceHeightFacet surfaceHeightFacet = new SurfaceHeightFacet(region.getRegion(), border);

        //Set the base surface height at each position covered by the facet
        Rect2i processRegion = surfaceHeightFacet.getWorldRegion();
        for (BaseVector2i position : processRegion.contents()) {
            surfaceHeightFacet.setWorld(position, 100);
        }

        //Apply the facet
        region.setRegionFacet(SurfaceHeightFacet.class, surfaceHeightFacet);
    }
}
