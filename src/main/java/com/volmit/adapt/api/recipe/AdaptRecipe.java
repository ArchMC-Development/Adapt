package com.volmit.adapt.api.recipe;

import com.volmit.adapt.Adapt;
import com.volmit.adapt.api.recipe.MaterialChar;
import manifold.rt.api.IBootstrap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.Plugin;

public interface AdaptRecipe {
    public static Shapeless.ShapelessBuilder shapeless() {
        return Shapeless.builder();
    }

    public static Shaped.ShapedBuilder shaped() {
        return Shaped.builder();
    }

    public static Smithing.SmithingBuilder smithing() {
        return Smithing.builder();
    }

    public static Stonecutter.StonecutterBuilder stonecutter() {
        return Stonecutter.builder();
    }

    public static Smoker.SmokerBuilder smoker() {
        return Smoker.builder();
    }

    public static Blast.BlastBuilder blast() {
        return Blast.builder();
    }

    public static Furnace.FurnaceBuilder furnace() {
        return Furnace.builder();
    }

    public static Campfire.CampfireBuilder campfire() {
        return Campfire.builder();
    }

    public ItemStack getResult();

    public String getKey();

    default public NamespacedKey getNSKey() {
        return new NamespacedKey((Plugin)Adapt.instance, this.getKey());
    }

    public void register();

    public boolean is(Recipe var1);

    public void unregister();

    public static class Shapeless
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private List<Material> ingredients;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result);
            this.ingredients.forEach(arg_0 -> ((ShapelessRecipe)shapelessRecipe).addIngredient(arg_0));
            Bukkit.getServer().addRecipe((Recipe)shapelessRecipe);
            Adapt.verbose("Registered Shapeless Crafting Recipe " + shapelessRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            ShapelessRecipe shapelessRecipe;
            return recipe instanceof ShapelessRecipe && (shapelessRecipe = (ShapelessRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Shapeless Crafting Recipe " + this.getKey());
        }

        Shapeless(String string, ItemStack itemStack, List<Material> list) {
            this.key = string;
            this.result = itemStack;
            this.ingredients = list;
        }

        public static ShapelessBuilder builder() {
            return new ShapelessBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public List<Material> getIngredients() {
            return this.ingredients;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredients(List<Material> list) {
            this.ingredients = list;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Shapeless)) {
                return false;
            }
            Shapeless shapeless = (Shapeless)object;
            if (!shapeless.canEqual(this)) {
                return false;
            }
            String string = this.getKey();
            String string2 = shapeless.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = shapeless.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            List<Material> list = this.getIngredients();
            List<Material> list2 = shapeless.getIngredients();
            return !(list == null ? list2 != null : !((Object)list).equals(list2));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Shapeless;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            List<Material> list = this.getIngredients();
            n2 = n2 * 59 + (list == null ? 43 : ((Object)list).hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Shapeless(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredients=" + this.getIngredients() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class ShapelessBuilder {
            private String key;
            private ItemStack result;
            private ArrayList<Material> ingredients;

            ShapelessBuilder() {
            }

            public ShapelessBuilder key(String string) {
                this.key = string;
                return this;
            }

            public ShapelessBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public ShapelessBuilder ingredient(Material material) {
                if (this.ingredients == null) {
                    this.ingredients = new ArrayList();
                }
                this.ingredients.add(material);
                return this;
            }

            public ShapelessBuilder ingredients(Collection<? extends Material> collection) {
                if (collection == null) {
                    throw new NullPointerException("ingredients cannot be null");
                }
                if (this.ingredients == null) {
                    this.ingredients = new ArrayList();
                }
                this.ingredients.addAll(collection);
                return this;
            }

            public ShapelessBuilder clearIngredients() {
                if (this.ingredients != null) {
                    this.ingredients.clear();
                }
                return this;
            }

            public Shapeless build() {
                return new Shapeless(this.key, this.result, switch (this.ingredients == null ? 0 : this.ingredients.size()) {
                    case 0 -> Collections.emptyList();
                    case 1 -> Collections.singletonList(this.ingredients.get(0));
                    default -> Collections.unmodifiableList(new ArrayList<Material>(this.ingredients));
                });
            }

            public String toString() {
                return "AdaptRecipe.Shapeless.ShapelessBuilder(key=" + this.key + ", result=" + this.result + ", ingredients=" + this.ingredients + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Shaped
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private List<MaterialChar> ingredients;
        private List<String> shapes;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result);
            shapedRecipe.shape(this.shapes.toArray(new String[0]));
            this.ingredients.forEach(materialChar -> shapedRecipe.setIngredient(materialChar.getCharacter(), materialChar.getChoice()));
            Bukkit.getServer().addRecipe((Recipe)shapedRecipe);
            Adapt.verbose("Registered Shaped Crafting Recipe " + shapedRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            ShapedRecipe shapedRecipe;
            return recipe instanceof ShapedRecipe && (shapedRecipe = (ShapedRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Shaped Crafting Recipe " + this.getKey());
        }

        Shaped(String string, ItemStack itemStack, List<MaterialChar> list, List<String> list2) {
            this.key = string;
            this.result = itemStack;
            this.ingredients = list;
            this.shapes = list2;
        }

        public static ShapedBuilder builder() {
            return new ShapedBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public List<MaterialChar> getIngredients() {
            return this.ingredients;
        }

        public List<String> getShapes() {
            return this.shapes;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredients(List<MaterialChar> list) {
            this.ingredients = list;
        }

        public void setShapes(List<String> list) {
            this.shapes = list;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Shaped)) {
                return false;
            }
            Shaped shaped = (Shaped)object;
            if (!shaped.canEqual(this)) {
                return false;
            }
            String string = this.getKey();
            String string2 = shaped.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = shaped.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            List<MaterialChar> list = this.getIngredients();
            List<MaterialChar> list2 = shaped.getIngredients();
            if (list == null ? list2 != null : !((Object)list).equals(list2)) {
                return false;
            }
            List<String> list3 = this.getShapes();
            List<String> list4 = shaped.getShapes();
            return !(list3 == null ? list4 != null : !((Object)list3).equals(list4));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Shaped;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            List<MaterialChar> list = this.getIngredients();
            n2 = n2 * 59 + (list == null ? 43 : ((Object)list).hashCode());
            List<String> list2 = this.getShapes();
            n2 = n2 * 59 + (list2 == null ? 43 : ((Object)list2).hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Shaped(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredients=" + this.getIngredients() + ", shapes=" + this.getShapes() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class ShapedBuilder {
            private String key;
            private ItemStack result;
            private ArrayList<MaterialChar> ingredients;
            private ArrayList<String> shapes;

            ShapedBuilder() {
            }

            public ShapedBuilder key(String string) {
                this.key = string;
                return this;
            }

            public ShapedBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public ShapedBuilder ingredient(MaterialChar materialChar) {
                if (this.ingredients == null) {
                    this.ingredients = new ArrayList();
                }
                this.ingredients.add(materialChar);
                return this;
            }

            public ShapedBuilder ingredients(Collection<? extends MaterialChar> collection) {
                if (collection == null) {
                    throw new NullPointerException("ingredients cannot be null");
                }
                if (this.ingredients == null) {
                    this.ingredients = new ArrayList();
                }
                this.ingredients.addAll(collection);
                return this;
            }

            public ShapedBuilder clearIngredients() {
                if (this.ingredients != null) {
                    this.ingredients.clear();
                }
                return this;
            }

            public ShapedBuilder shape(String string) {
                if (this.shapes == null) {
                    this.shapes = new ArrayList();
                }
                this.shapes.add(string);
                return this;
            }

            public ShapedBuilder shapes(Collection<? extends String> collection) {
                if (collection == null) {
                    throw new NullPointerException("shapes cannot be null");
                }
                if (this.shapes == null) {
                    this.shapes = new ArrayList();
                }
                this.shapes.addAll(collection);
                return this;
            }

            public ShapedBuilder clearShapes() {
                if (this.shapes != null) {
                    this.shapes.clear();
                }
                return this;
            }

            public Shaped build() {
                return new Shaped(this.key, this.result, switch (this.ingredients == null ? 0 : this.ingredients.size()) {
                    case 0 -> Collections.emptyList();
                    case 1 -> Collections.singletonList(this.ingredients.get(0));
                    default -> Collections.unmodifiableList(new ArrayList<MaterialChar>(this.ingredients));
                }, switch (this.shapes == null ? 0 : this.shapes.size()) {
                    case 0 -> Collections.emptyList();
                    case 1 -> Collections.singletonList(this.shapes.get(0));
                    default -> Collections.unmodifiableList(new ArrayList<String>(this.shapes));
                });
            }

            public String toString() {
                return "AdaptRecipe.Shaped.ShapedBuilder(key=" + this.key + ", result=" + this.result + ", ingredients=" + this.ingredients + ", shapes=" + this.shapes + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Smithing
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private Material base;
        private Material addition;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            SmithingRecipe smithingRecipe = new SmithingRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result, (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(this.base)), (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(this.addition)));
            Bukkit.getServer().addRecipe((Recipe)smithingRecipe);
            Adapt.verbose("Registered Smithing Table Recipe " + smithingRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            SmithingRecipe smithingRecipe;
            return recipe instanceof SmithingRecipe && (smithingRecipe = (SmithingRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Smithing Table Recipe " + this.getKey());
        }

        Smithing(String string, ItemStack itemStack, Material material, Material material2) {
            this.key = string;
            this.result = itemStack;
            this.base = material;
            this.addition = material2;
        }

        public static SmithingBuilder builder() {
            return new SmithingBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public Material getBase() {
            return this.base;
        }

        public Material getAddition() {
            return this.addition;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setBase(Material material) {
            this.base = material;
        }

        public void setAddition(Material material) {
            this.addition = material;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Smithing)) {
                return false;
            }
            Smithing smithing = (Smithing)object;
            if (!smithing.canEqual(this)) {
                return false;
            }
            String string = this.getKey();
            String string2 = smithing.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = smithing.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            Material material = this.getBase();
            Material material2 = smithing.getBase();
            if (material == null ? material2 != null : !material.equals(material2)) {
                return false;
            }
            Material material3 = this.getAddition();
            Material material4 = smithing.getAddition();
            return !(material3 == null ? material4 != null : !material3.equals(material4));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Smithing;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            Material material = this.getBase();
            n2 = n2 * 59 + (material == null ? 43 : material.hashCode());
            Material material2 = this.getAddition();
            n2 = n2 * 59 + (material2 == null ? 43 : material2.hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Smithing(key=" + this.getKey() + ", result=" + this.getResult() + ", base=" + this.getBase() + ", addition=" + this.getAddition() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class SmithingBuilder {
            private String key;
            private ItemStack result;
            private Material base;
            private Material addition;

            SmithingBuilder() {
            }

            public SmithingBuilder key(String string) {
                this.key = string;
                return this;
            }

            public SmithingBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public SmithingBuilder base(Material material) {
                this.base = material;
                return this;
            }

            public SmithingBuilder addition(Material material) {
                this.addition = material;
                return this;
            }

            public Smithing build() {
                return new Smithing(this.key, this.result, this.base, this.addition);
            }

            public String toString() {
                return "AdaptRecipe.Smithing.SmithingBuilder(key=" + this.key + ", result=" + this.result + ", base=" + this.base + ", addition=" + this.addition + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Stonecutter
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private Material ingredient;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            StonecuttingRecipe stonecuttingRecipe = new StonecuttingRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result, this.ingredient);
            Bukkit.getServer().addRecipe((Recipe)stonecuttingRecipe);
            Adapt.verbose("Registered Stone Cutter Recipe " + stonecuttingRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            StonecuttingRecipe stonecuttingRecipe;
            return recipe instanceof StonecuttingRecipe && (stonecuttingRecipe = (StonecuttingRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Stone Cutter Recipe " + this.getKey());
        }

        Stonecutter(String string, ItemStack itemStack, Material material) {
            this.key = string;
            this.result = itemStack;
            this.ingredient = material;
        }

        public static StonecutterBuilder builder() {
            return new StonecutterBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public Material getIngredient() {
            return this.ingredient;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredient(Material material) {
            this.ingredient = material;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Stonecutter)) {
                return false;
            }
            Stonecutter stonecutter = (Stonecutter)object;
            if (!stonecutter.canEqual(this)) {
                return false;
            }
            String string = this.getKey();
            String string2 = stonecutter.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = stonecutter.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            Material material = this.getIngredient();
            Material material2 = stonecutter.getIngredient();
            return !(material == null ? material2 != null : !material.equals(material2));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Stonecutter;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            Material material = this.getIngredient();
            n2 = n2 * 59 + (material == null ? 43 : material.hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Stonecutter(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredient=" + this.getIngredient() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class StonecutterBuilder {
            private String key;
            private ItemStack result;
            private Material ingredient;

            StonecutterBuilder() {
            }

            public StonecutterBuilder key(String string) {
                this.key = string;
                return this;
            }

            public StonecutterBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public StonecutterBuilder ingredient(Material material) {
                this.ingredient = material;
                return this;
            }

            public Stonecutter build() {
                return new Stonecutter(this.key, this.result, this.ingredient);
            }

            public String toString() {
                return "AdaptRecipe.Stonecutter.StonecutterBuilder(key=" + this.key + ", result=" + this.result + ", ingredient=" + this.ingredient + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Smoker
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private Material ingredient;
        private float experience;
        private int cookTime;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            SmokingRecipe smokingRecipe = new SmokingRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result, this.ingredient, this.experience, this.cookTime);
            Bukkit.getServer().addRecipe((Recipe)smokingRecipe);
            Adapt.verbose("Registered Smoker Recipe " + smokingRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            SmokingRecipe smokingRecipe;
            return recipe instanceof SmokingRecipe && (smokingRecipe = (SmokingRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Smoker Recipe " + this.getKey());
        }

        Smoker(String string, ItemStack itemStack, Material material, float f, int n) {
            this.key = string;
            this.result = itemStack;
            this.ingredient = material;
            this.experience = f;
            this.cookTime = n;
        }

        public static SmokerBuilder builder() {
            return new SmokerBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public Material getIngredient() {
            return this.ingredient;
        }

        public float getExperience() {
            return this.experience;
        }

        public int getCookTime() {
            return this.cookTime;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredient(Material material) {
            this.ingredient = material;
        }

        public void setExperience(float f) {
            this.experience = f;
        }

        public void setCookTime(int n) {
            this.cookTime = n;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Smoker)) {
                return false;
            }
            Smoker smoker = (Smoker)object;
            if (!smoker.canEqual(this)) {
                return false;
            }
            if (Float.compare(this.getExperience(), smoker.getExperience()) != 0) {
                return false;
            }
            if (this.getCookTime() != smoker.getCookTime()) {
                return false;
            }
            String string = this.getKey();
            String string2 = smoker.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = smoker.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            Material material = this.getIngredient();
            Material material2 = smoker.getIngredient();
            return !(material == null ? material2 != null : !material.equals(material2));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Smoker;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            n2 = n2 * 59 + Float.floatToIntBits(this.getExperience());
            n2 = n2 * 59 + this.getCookTime();
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            Material material = this.getIngredient();
            n2 = n2 * 59 + (material == null ? 43 : material.hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Smoker(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredient=" + this.getIngredient() + ", experience=" + this.getExperience() + ", cookTime=" + this.getCookTime() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class SmokerBuilder {
            private String key;
            private ItemStack result;
            private Material ingredient;
            private float experience;
            private int cookTime;

            SmokerBuilder() {
            }

            public SmokerBuilder key(String string) {
                this.key = string;
                return this;
            }

            public SmokerBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public SmokerBuilder ingredient(Material material) {
                this.ingredient = material;
                return this;
            }

            public SmokerBuilder experience(float f) {
                this.experience = f;
                return this;
            }

            public SmokerBuilder cookTime(int n) {
                this.cookTime = n;
                return this;
            }

            public Smoker build() {
                return new Smoker(this.key, this.result, this.ingredient, this.experience, this.cookTime);
            }

            public String toString() {
                return "AdaptRecipe.Smoker.SmokerBuilder(key=" + this.key + ", result=" + this.result + ", ingredient=" + this.ingredient + ", experience=" + this.experience + ", cookTime=" + this.cookTime + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Blast
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private Material ingredient;
        private float experience;
        private int cookTime;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            BlastingRecipe blastingRecipe = new BlastingRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result, this.ingredient, this.experience, this.cookTime);
            Bukkit.getServer().addRecipe((Recipe)blastingRecipe);
            Adapt.verbose("Registered Blast Furnace Recipe " + blastingRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            BlastingRecipe blastingRecipe;
            return recipe instanceof BlastingRecipe && (blastingRecipe = (BlastingRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Blast Furnace Recipe " + this.getKey());
        }

        Blast(String string, ItemStack itemStack, Material material, float f, int n) {
            this.key = string;
            this.result = itemStack;
            this.ingredient = material;
            this.experience = f;
            this.cookTime = n;
        }

        public static BlastBuilder builder() {
            return new BlastBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public Material getIngredient() {
            return this.ingredient;
        }

        public float getExperience() {
            return this.experience;
        }

        public int getCookTime() {
            return this.cookTime;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredient(Material material) {
            this.ingredient = material;
        }

        public void setExperience(float f) {
            this.experience = f;
        }

        public void setCookTime(int n) {
            this.cookTime = n;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Blast)) {
                return false;
            }
            Blast blast = (Blast)object;
            if (!blast.canEqual(this)) {
                return false;
            }
            if (Float.compare(this.getExperience(), blast.getExperience()) != 0) {
                return false;
            }
            if (this.getCookTime() != blast.getCookTime()) {
                return false;
            }
            String string = this.getKey();
            String string2 = blast.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = blast.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            Material material = this.getIngredient();
            Material material2 = blast.getIngredient();
            return !(material == null ? material2 != null : !material.equals(material2));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Blast;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            n2 = n2 * 59 + Float.floatToIntBits(this.getExperience());
            n2 = n2 * 59 + this.getCookTime();
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            Material material = this.getIngredient();
            n2 = n2 * 59 + (material == null ? 43 : material.hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Blast(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredient=" + this.getIngredient() + ", experience=" + this.getExperience() + ", cookTime=" + this.getCookTime() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class BlastBuilder {
            private String key;
            private ItemStack result;
            private Material ingredient;
            private float experience;
            private int cookTime;

            BlastBuilder() {
            }

            public BlastBuilder key(String string) {
                this.key = string;
                return this;
            }

            public BlastBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public BlastBuilder ingredient(Material material) {
                this.ingredient = material;
                return this;
            }

            public BlastBuilder experience(float f) {
                this.experience = f;
                return this;
            }

            public BlastBuilder cookTime(int n) {
                this.cookTime = n;
                return this;
            }

            public Blast build() {
                return new Blast(this.key, this.result, this.ingredient, this.experience, this.cookTime);
            }

            public String toString() {
                return "AdaptRecipe.Blast.BlastBuilder(key=" + this.key + ", result=" + this.result + ", ingredient=" + this.ingredient + ", experience=" + this.experience + ", cookTime=" + this.cookTime + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Furnace
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private Material ingredient;
        private float experience;
        private int cookTime;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            FurnaceRecipe furnaceRecipe = new FurnaceRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result, this.ingredient, this.experience, this.cookTime);
            Bukkit.getServer().addRecipe((Recipe)furnaceRecipe);
            Adapt.verbose("Registered Furnace Recipe " + furnaceRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            FurnaceRecipe furnaceRecipe;
            return recipe instanceof FurnaceRecipe && (furnaceRecipe = (FurnaceRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Furnace Recipe " + this.getKey());
        }

        Furnace(String string, ItemStack itemStack, Material material, float f, int n) {
            this.key = string;
            this.result = itemStack;
            this.ingredient = material;
            this.experience = f;
            this.cookTime = n;
        }

        public static FurnaceBuilder builder() {
            return new FurnaceBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public Material getIngredient() {
            return this.ingredient;
        }

        public float getExperience() {
            return this.experience;
        }

        public int getCookTime() {
            return this.cookTime;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredient(Material material) {
            this.ingredient = material;
        }

        public void setExperience(float f) {
            this.experience = f;
        }

        public void setCookTime(int n) {
            this.cookTime = n;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Furnace)) {
                return false;
            }
            Furnace furnace = (Furnace)object;
            if (!furnace.canEqual(this)) {
                return false;
            }
            if (Float.compare(this.getExperience(), furnace.getExperience()) != 0) {
                return false;
            }
            if (this.getCookTime() != furnace.getCookTime()) {
                return false;
            }
            String string = this.getKey();
            String string2 = furnace.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = furnace.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            Material material = this.getIngredient();
            Material material2 = furnace.getIngredient();
            return !(material == null ? material2 != null : !material.equals(material2));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Furnace;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            n2 = n2 * 59 + Float.floatToIntBits(this.getExperience());
            n2 = n2 * 59 + this.getCookTime();
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            Material material = this.getIngredient();
            n2 = n2 * 59 + (material == null ? 43 : material.hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Furnace(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredient=" + this.getIngredient() + ", experience=" + this.getExperience() + ", cookTime=" + this.getCookTime() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class FurnaceBuilder {
            private String key;
            private ItemStack result;
            private Material ingredient;
            private float experience;
            private int cookTime;

            FurnaceBuilder() {
            }

            public FurnaceBuilder key(String string) {
                this.key = string;
                return this;
            }

            public FurnaceBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public FurnaceBuilder ingredient(Material material) {
                this.ingredient = material;
                return this;
            }

            public FurnaceBuilder experience(float f) {
                this.experience = f;
                return this;
            }

            public FurnaceBuilder cookTime(int n) {
                this.cookTime = n;
                return this;
            }

            public Furnace build() {
                return new Furnace(this.key, this.result, this.ingredient, this.experience, this.cookTime);
            }

            public String toString() {
                return "AdaptRecipe.Furnace.FurnaceBuilder(key=" + this.key + ", result=" + this.result + ", ingredient=" + this.ingredient + ", experience=" + this.experience + ", cookTime=" + this.cookTime + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }

    public static class Campfire
            implements AdaptRecipe {
        private String key;
        private ItemStack result;
        private Material ingredient;
        private float experience;
        private int cookTime;

        @Override
        public ItemStack getResult() {
            return null;
        }

        @Override
        public void register() {
            CampfireRecipe campfireRecipe = new CampfireRecipe(new NamespacedKey((Plugin)Adapt.instance, this.getKey()), this.result, this.ingredient, this.experience, this.cookTime);
            Bukkit.getServer().addRecipe((Recipe)campfireRecipe);
            Adapt.verbose("Registered Campfire Recipe " + campfireRecipe.getKey());
        }

        @Override
        public boolean is(Recipe recipe) {
            CampfireRecipe campfireRecipe;
            return recipe instanceof CampfireRecipe && (campfireRecipe = (CampfireRecipe)recipe).getKey().equals((Object)this.getNSKey());
        }

        @Override
        public void unregister() {
            Bukkit.getServer().removeRecipe(this.getNSKey());
            Adapt.verbose("Unregistered Campfire Recipe " + this.getKey());
        }

        Campfire(String string, ItemStack itemStack, Material material, float f, int n) {
            this.key = string;
            this.result = itemStack;
            this.ingredient = material;
            this.experience = f;
            this.cookTime = n;
        }

        public static CampfireBuilder builder() {
            return new CampfireBuilder();
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public Material getIngredient() {
            return this.ingredient;
        }

        public float getExperience() {
            return this.experience;
        }

        public int getCookTime() {
            return this.cookTime;
        }

        public void setKey(String string) {
            this.key = string;
        }

        public void setResult(ItemStack itemStack) {
            this.result = itemStack;
        }

        public void setIngredient(Material material) {
            this.ingredient = material;
        }

        public void setExperience(float f) {
            this.experience = f;
        }

        public void setCookTime(int n) {
            this.cookTime = n;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Campfire)) {
                return false;
            }
            Campfire campfire = (Campfire)object;
            if (!campfire.canEqual(this)) {
                return false;
            }
            if (Float.compare(this.getExperience(), campfire.getExperience()) != 0) {
                return false;
            }
            if (this.getCookTime() != campfire.getCookTime()) {
                return false;
            }
            String string = this.getKey();
            String string2 = campfire.getKey();
            if (string == null ? string2 != null : !string.equals(string2)) {
                return false;
            }
            ItemStack itemStack = this.getResult();
            ItemStack itemStack2 = campfire.getResult();
            if (itemStack == null ? itemStack2 != null : !itemStack.equals(itemStack2)) {
                return false;
            }
            Material material = this.getIngredient();
            Material material2 = campfire.getIngredient();
            return !(material == null ? material2 != null : !material.equals(material2));
        }

        protected boolean canEqual(Object object) {
            return object instanceof Campfire;
        }

        public int hashCode() {
            int n = 59;
            int n2 = 1;
            n2 = n2 * 59 + Float.floatToIntBits(this.getExperience());
            n2 = n2 * 59 + this.getCookTime();
            String string = this.getKey();
            n2 = n2 * 59 + (string == null ? 43 : string.hashCode());
            ItemStack itemStack = this.getResult();
            n2 = n2 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
            Material material = this.getIngredient();
            n2 = n2 * 59 + (material == null ? 43 : material.hashCode());
            return n2;
        }

        public String toString() {
            return "AdaptRecipe.Campfire(key=" + this.getKey() + ", result=" + this.getResult() + ", ingredient=" + this.getIngredient() + ", experience=" + this.getExperience() + ", cookTime=" + this.getCookTime() + ")";
        }

        static {
            IBootstrap.dasBoot();
        }

        public static class CampfireBuilder {
            private String key;
            private ItemStack result;
            private Material ingredient;
            private float experience;
            private int cookTime;

            CampfireBuilder() {
            }

            public CampfireBuilder key(String string) {
                this.key = string;
                return this;
            }

            public CampfireBuilder result(ItemStack itemStack) {
                this.result = itemStack;
                return this;
            }

            public CampfireBuilder ingredient(Material material) {
                this.ingredient = material;
                return this;
            }

            public CampfireBuilder experience(float f) {
                this.experience = f;
                return this;
            }

            public CampfireBuilder cookTime(int n) {
                this.cookTime = n;
                return this;
            }

            public Campfire build() {
                return new Campfire(this.key, this.result, this.ingredient, this.experience, this.cookTime);
            }

            public String toString() {
                return "AdaptRecipe.Campfire.CampfireBuilder(key=" + this.key + ", result=" + this.result + ", ingredient=" + this.ingredient + ", experience=" + this.experience + ", cookTime=" + this.cookTime + ")";
            }

            static {
                IBootstrap.dasBoot();
            }
        }
    }
}
