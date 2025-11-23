package com.sember.revelation.registry;

import com.sember.revelation.Revelation;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class RevelationTags {

    public static final TagKey<Block> NETHER = TagKey.of(RegistryKeys.BLOCK, Revelation.id("nether"));

}
