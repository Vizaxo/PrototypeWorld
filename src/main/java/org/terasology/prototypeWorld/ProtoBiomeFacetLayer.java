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

import org.terasology.world.viewer.layers.NominalFacetLayer;
import org.terasology.world.viewer.layers.Renders;
import org.terasology.world.viewer.layers.ZOrder;

@Renders(value = ProtoBiomeFacet.class, order = ZOrder.BIOME)
public class ProtoBiomeFacetLayer extends NominalFacetLayer<ProtoBiome> {

    public ProtoBiomeFacetLayer() {
        super(ProtoBiomeFacet.class, biome -> biome.getPreviewColor().alterAlpha(10));
    }
}
