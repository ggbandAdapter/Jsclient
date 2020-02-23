package com.starteos.jsbridge.tron.model;

import java.util.List;

public class TronAccount {


    /**
     * address : 41f4616fb2b03f76eeba7591d265fcc3f04cef9b42
     * balance : 429934580
     * frozen : [{"frozen_balance":107000000,"expire_time":1579418598000}]
     * create_time : 1573461603000
     * latest_opration_time : 1580795799000
     * allowance : 10195
     * latest_consume_free_time : 1580795799000
     * account_resource : {"latest_consume_time_for_energy":1579318695000,"delegated_frozen_balance_for_energy":50000000}
     * owner_permission : {"permission_name":"owner","threshold":1,"keys":[{"address":"41f4616fb2b03f76eeba7591d265fcc3f04cef9b42","weight":1}]}
     * active_permission : [{"type":"Active","id":2,"permission_name":"active","threshold":1,"operations":"7fff1fc0033e0300000000000000000000000000000000000000000000000000","keys":[{"address":"41f4616fb2b03f76eeba7591d265fcc3f04cef9b42","weight":1}]}]
     * acquired_delegated_frozen_balance_for_bandwidth : 1000000
     * assetV2 : [{"key":"1002742","value":6000010},{"key":"1002775","value":10000000},{"key":"1002798","value":10000000},{"key":"1002000","value":13544185},{"key":"1002636","value":10000000},{"key":"1002814","value":10000000},{"key":"1002830","value":10000000},{"key":"1002845","value":10000000},{"key":"1002858","value":10000000},{"key":"1002881","value":10000000}]
     * free_asset_net_usageV2 : [{"key":"1002742","value":0},{"key":"1002775","value":0},{"key":"1002798","value":0},{"key":"1002000","value":0},{"key":"1002636","value":0},{"key":"1002814","value":0},{"key":"1002830","value":0},{"key":"1002845","value":0},{"key":"1002858","value":0},{"key":"1002881","value":0}]
     * account : ggband
     */

    private String address;
    private int balance;
    private long create_time;
    private long latest_opration_time;
    private int allowance;
    private long latest_consume_free_time;
    private AccountResourceBean account_resource;
    private OwnerPermissionBean owner_permission;
    private int acquired_delegated_frozen_balance_for_bandwidth;
    private String account;
    private List<FrozenBean> frozen;
    private List<ActivePermissionBean> active_permission;
    private List<AssetV2Bean> assetV2;
    private List<FreeAssetNetUsageV2Bean> free_asset_net_usageV2;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getLatest_opration_time() {
        return latest_opration_time;
    }

    public void setLatest_opration_time(long latest_opration_time) {
        this.latest_opration_time = latest_opration_time;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public long getLatest_consume_free_time() {
        return latest_consume_free_time;
    }

    public void setLatest_consume_free_time(long latest_consume_free_time) {
        this.latest_consume_free_time = latest_consume_free_time;
    }

    public AccountResourceBean getAccount_resource() {
        return account_resource;
    }

    public void setAccount_resource(AccountResourceBean account_resource) {
        this.account_resource = account_resource;
    }

    public OwnerPermissionBean getOwner_permission() {
        return owner_permission;
    }

    public void setOwner_permission(OwnerPermissionBean owner_permission) {
        this.owner_permission = owner_permission;
    }

    public int getAcquired_delegated_frozen_balance_for_bandwidth() {
        return acquired_delegated_frozen_balance_for_bandwidth;
    }

    public void setAcquired_delegated_frozen_balance_for_bandwidth(int acquired_delegated_frozen_balance_for_bandwidth) {
        this.acquired_delegated_frozen_balance_for_bandwidth = acquired_delegated_frozen_balance_for_bandwidth;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<FrozenBean> getFrozen() {
        return frozen;
    }

    public void setFrozen(List<FrozenBean> frozen) {
        this.frozen = frozen;
    }

    public List<ActivePermissionBean> getActive_permission() {
        return active_permission;
    }

    public void setActive_permission(List<ActivePermissionBean> active_permission) {
        this.active_permission = active_permission;
    }

    public List<AssetV2Bean> getAssetV2() {
        return assetV2;
    }

    public void setAssetV2(List<AssetV2Bean> assetV2) {
        this.assetV2 = assetV2;
    }

    public List<FreeAssetNetUsageV2Bean> getFree_asset_net_usageV2() {
        return free_asset_net_usageV2;
    }

    public void setFree_asset_net_usageV2(List<FreeAssetNetUsageV2Bean> free_asset_net_usageV2) {
        this.free_asset_net_usageV2 = free_asset_net_usageV2;
    }

    public static class AccountResourceBean {
        /**
         * latest_consume_time_for_energy : 1579318695000
         * delegated_frozen_balance_for_energy : 50000000
         */

        private long latest_consume_time_for_energy;
        private int delegated_frozen_balance_for_energy;

        public long getLatest_consume_time_for_energy() {
            return latest_consume_time_for_energy;
        }

        public void setLatest_consume_time_for_energy(long latest_consume_time_for_energy) {
            this.latest_consume_time_for_energy = latest_consume_time_for_energy;
        }

        public int getDelegated_frozen_balance_for_energy() {
            return delegated_frozen_balance_for_energy;
        }

        public void setDelegated_frozen_balance_for_energy(int delegated_frozen_balance_for_energy) {
            this.delegated_frozen_balance_for_energy = delegated_frozen_balance_for_energy;
        }
    }

    public static class OwnerPermissionBean {
        /**
         * permission_name : owner
         * threshold : 1
         * keys : [{"address":"41f4616fb2b03f76eeba7591d265fcc3f04cef9b42","weight":1}]
         */

        private String permission_name;
        private int threshold;
        private List<KeysBean> keys;

        public String getPermission_name() {
            return permission_name;
        }

        public void setPermission_name(String permission_name) {
            this.permission_name = permission_name;
        }

        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }

        public List<KeysBean> getKeys() {
            return keys;
        }

        public void setKeys(List<KeysBean> keys) {
            this.keys = keys;
        }

        public static class KeysBean {
            /**
             * address : 41f4616fb2b03f76eeba7591d265fcc3f04cef9b42
             * weight : 1
             */

            private String address;
            private int weight;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }
        }
    }

    public static class FrozenBean {
        /**
         * frozen_balance : 107000000
         * expire_time : 1579418598000
         */

        private int frozen_balance;
        private long expire_time;

        public int getFrozen_balance() {
            return frozen_balance;
        }

        public void setFrozen_balance(int frozen_balance) {
            this.frozen_balance = frozen_balance;
        }

        public long getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(long expire_time) {
            this.expire_time = expire_time;
        }
    }

    public static class ActivePermissionBean {
        /**
         * type : Active
         * id : 2
         * permission_name : active
         * threshold : 1
         * operations : 7fff1fc0033e0300000000000000000000000000000000000000000000000000
         * keys : [{"address":"41f4616fb2b03f76eeba7591d265fcc3f04cef9b42","weight":1}]
         */

        private String type;
        private int id;
        private String permission_name;
        private int threshold;
        private String operations;
        private List<KeysBeanX> keys;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPermission_name() {
            return permission_name;
        }

        public void setPermission_name(String permission_name) {
            this.permission_name = permission_name;
        }

        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }

        public String getOperations() {
            return operations;
        }

        public void setOperations(String operations) {
            this.operations = operations;
        }

        public List<KeysBeanX> getKeys() {
            return keys;
        }

        public void setKeys(List<KeysBeanX> keys) {
            this.keys = keys;
        }

        public static class KeysBeanX {
            /**
             * address : 41f4616fb2b03f76eeba7591d265fcc3f04cef9b42
             * weight : 1
             */

            private String address;
            private int weight;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }
        }
    }

    public static class AssetV2Bean {
        /**
         * key : 1002742
         * value : 6000010
         */

        private String key;
        private int value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class FreeAssetNetUsageV2Bean {
        /**
         * key : 1002742
         * value : 0
         */

        private String key;
        private int value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



}
