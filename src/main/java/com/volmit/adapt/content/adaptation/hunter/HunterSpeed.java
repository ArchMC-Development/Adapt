/*------------------------------------------------------------------------------
 -   Adapt is a Skill/Integration plugin  for Minecraft Bukkit Servers
 -   Copyright (c) 2022 Arcane Arts (Volmit Software)
 -
 -   This program is free software: you can redistribute it and/or modify
 -   it under the terms of the GNU General Public License as published by
 -   the Free Software Foundation, either version 3 of the License, or
 -   (at your option) any later version.
 -
 -   This program is distributed in the hope that it will be useful,
 -   but WITHOUT ANY WARRANTY; without even the implied warranty of
 -   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 -   GNU General Public License for more details.
 -
 -   You should have received a copy of the GNU General Public License
 -   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 -----------------------------------------------------------------------------*/

package com.volmit.adapt.content.adaptation.hunter;

import com.volmit.adapt.Adapt;
import com.volmit.adapt.api.adaptation.SimpleAdaptation;
import com.volmit.adapt.util.C;
import com.volmit.adapt.util.Element;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class HunterSpeed extends SimpleAdaptation<HunterSpeed.Config> {
    public HunterSpeed() {
        super("hunter-speed");
        registerConfiguration(Config.class);
        setDescription(Adapt.dLocalize("hunter", "speed", "description"));
        setDisplayName(Adapt.dLocalize("hunter", "speed", "name"));
        setIcon(Material.LAVA_BUCKET);
        setBaseCost(getConfig().baseCost);
        setMaxLevel(getConfig().maxLevel);
        setInitialCost(getConfig().initialCost);
        setCostFactor(getConfig().costFactor);
        setInterval(9844);
    }

    @Override
    public void addStats(int level, Element v) {
        v.addLore(C.GRAY + Adapt.dLocalize("hunter", "speed", "lore1"));
        v.addLore(C.GREEN + "+ " + level + C.GRAY + Adapt.dLocalize("hunter", "speed", "lore2"));
        v.addLore(C.RED + "- " + 5 + level + C.GRAY + Adapt.dLocalize("hunter", "speed", "lore3"));
        v.addLore(C.GRAY + "* " + level + C.GRAY + " " + Adapt.dLocalize("hunter", "speed", "lore4"));
        v.addLore(C.GRAY + "* " + level + C.GRAY + " " + Adapt.dLocalize("hunter", "speed", "lore5"));
        v.addLore(C.GRAY + "- " + level + C.RED + " " + Adapt.dLocalize("hunter", "penalty", "lore1"));

    }


    @EventHandler
    public void on(EntityDamageEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getEntity() instanceof org.bukkit.entity.Player p && !e.getCause().equals(EntityDamageEvent.DamageCause.STARVATION) && hasAdaptation(p)) {
            if (!getConfig().useConsumable) {

                if (p.getFoodLevel() == 0) {
                    addPotionStacks(p, PotionEffectType.POISON, 2 + getLevel(p), 300, true);

                } else {
                    addPotionStacks(p, PotionEffectType.HUNGER, 5 + getLevel(p), 100, true);
                    addPotionStacks(p, PotionEffectType.SPEED, getLevel(p), 50, false);
                }
            } else {
                if (getConfig().consumable != null && Material.getMaterial(getConfig().consumable) != null) {
                    Material mat = Material.getMaterial(getConfig().consumable);
                    if (p.getInventory().contains(mat)){
                        p.getInventory().removeItem(new ItemStack(mat, 1));
                        addPotionStacks(p, PotionEffectType.SPEED, getLevel(p), 50, false);
                    } else {
                        addPotionStacks(p, PotionEffectType.POISON, 2 + getLevel(p), 300, true);
                    }
                }
            }
        }
    }

    @Override
    public void onTick() {

    }

    @Override
    public boolean isEnabled() {
        return getConfig().enabled;
    }

    @Override
    public boolean isPermanent() {
        return getConfig().permanent;
    }

    @NoArgsConstructor
    protected static class Config {
        boolean permanent = false;
        boolean enabled = true;
        boolean useConsumable = false;
        String consumable = "ROTTEN_FLESH";
        int baseCost = 4;
        int maxLevel = 5;
        int initialCost = 8;
        double costFactor = 0.4;
    }
}
