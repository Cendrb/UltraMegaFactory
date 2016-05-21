package com.mandatoryfun.ultramegafactory.lib;

import net.minecraft.util.IStringSerializable;

/**
 * Created by cendr_000 on 21.05.2016.
 */
public enum CasingModelType implements IStringSerializable {
    UNE {
        @Override
        public String getName() {
            return "UNE";
        }
    }, UNW {
        @Override
        public String getName() {
            return "UNW";
        }
    }, USE {
        @Override
        public String getName() {
            return "USE";
        }
    }, USW {
        @Override
        public String getName() {
            return "USW";
        }
    }, DNE {
        @Override
        public String getName() {
            return "DNE";
        }
    }, DNW {
        @Override
        public String getName() {
            return "DNW";
        }
    }, DSE {
        @Override
        public String getName() {
            return "DSE";
        }
    }, DSW {
        @Override
        public String getName() {
            return "DSW";
        }
    }, SIDE {
        @Override
        public String getName() {
            return "SIDE";
        }
    }
}
