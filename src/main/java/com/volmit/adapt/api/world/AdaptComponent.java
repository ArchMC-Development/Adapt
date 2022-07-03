package com.volmit.adapt.api.world;

import com.volmit.adapt.Adapt;
import com.volmit.adapt.AdaptMantle;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface AdaptComponent {
    default AdaptServer getServer() {
        return Adapt.instance.getAdaptServer();
    }

    default AdaptMantle getMantle() {
        return getServer().getMantle();
    }

    default AdaptPlayer getPlayer(Player p) {
        return getServer().getPlayer(p);
    }

    default boolean isItem(ItemStack is) {
        return is != null && !is.getType().equals(Material.AIR);
    }

    default boolean isTool(ItemStack is) {
        return isAxe(is) || isPickaxe(is) || isHoe(is) || isShovel(is) || isSword(is);
    }

    default boolean isMelee(ItemStack is) {
        return isTool(is);
    }

    default boolean isRanged(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case BOW, CROSSBOW -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isSword(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_SWORD, GOLDEN_SWORD, IRON_SWORD, NETHERITE_SWORD, STONE_SWORD, WOODEN_SWORD -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isAxe(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_AXE, GOLDEN_AXE, IRON_AXE, NETHERITE_AXE, STONE_AXE, WOODEN_AXE -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isPickaxe(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_PICKAXE, GOLDEN_PICKAXE, IRON_PICKAXE, NETHERITE_PICKAXE, STONE_PICKAXE, WOODEN_PICKAXE -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isShovel(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_SHOVEL, GOLDEN_SHOVEL, IRON_SHOVEL, NETHERITE_SHOVEL, STONE_SHOVEL, WOODEN_SHOVEL -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isBoots(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_BOOTS, GOLDEN_BOOTS, IRON_BOOTS, NETHERITE_BOOTS, CHAINMAIL_BOOTS, LEATHER_BOOTS -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isHelmet(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case CHAINMAIL_HELMET, DIAMOND_HELMET, GOLDEN_HELMET, IRON_HELMET, LEATHER_HELMET, NETHERITE_HELMET, TURTLE_HELMET -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isLeggings(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_LEGGINGS, GOLDEN_LEGGINGS, IRON_LEGGINGS, NETHERITE_LEGGINGS, CHAINMAIL_LEGGINGS, LEATHER_LEGGINGS -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isChestplate(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_CHESTPLATE, GOLDEN_CHESTPLATE, IRON_CHESTPLATE, NETHERITE_CHESTPLATE, CHAINMAIL_CHESTPLATE, LEATHER_CHESTPLATE -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isHoe(ItemStack it) {
        if(isItem(it)) {
            return switch(it.getType()) {
                case DIAMOND_HOE, GOLDEN_HOE, IRON_HOE, NETHERITE_HOE, STONE_HOE, WOODEN_HOE -> true;
                default -> false;
            };
        }

        return false;
    }

    default boolean isOre(BlockData b) {
        return switch(b.getMaterial()) {
            case COPPER_ORE, DEEPSLATE_COPPER_ORE, COAL_ORE, GOLD_ORE, IRON_ORE, DIAMOND_ORE, LAPIS_ORE, EMERALD_ORE, NETHER_QUARTZ_ORE, NETHER_GOLD_ORE, REDSTONE_ORE, DEEPSLATE_COAL_ORE, DEEPSLATE_IRON_ORE, DEEPSLATE_GOLD_ORE, DEEPSLATE_LAPIS_ORE, DEEPSLATE_DIAMOND_ORE, DEEPSLATE_EMERALD_ORE, DEEPSLATE_REDSTONE_ORE -> true;
            default -> false;
        };
    }

    default boolean isStorage(BlockData b) {
        return switch(b.getMaterial()) {
            case CHEST,
                SMOKER,
                TRAPPED_CHEST,
                SHULKER_BOX,
                WHITE_SHULKER_BOX,
                ORANGE_SHULKER_BOX,
                MAGENTA_SHULKER_BOX,
                LIGHT_BLUE_SHULKER_BOX,
                YELLOW_SHULKER_BOX,
                LIME_SHULKER_BOX,
                PINK_SHULKER_BOX,
                GRAY_SHULKER_BOX,
                LIGHT_GRAY_SHULKER_BOX,
                CYAN_SHULKER_BOX,
                PURPLE_SHULKER_BOX,
                BLUE_SHULKER_BOX,
                BROWN_SHULKER_BOX,
                GREEN_SHULKER_BOX,
                RED_SHULKER_BOX,
                BLACK_SHULKER_BOX,
                BARREL,
                DISPENSER,
                DROPPER,
                FURNACE,
                BLAST_FURNACE,
                HOPPER -> true;
            default -> false;
        };
    }
}
