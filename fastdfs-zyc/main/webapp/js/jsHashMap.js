/**
* used like java.lang.HashMap
*/
/**
* HashMap构造函数
*/
function HashMap()
{
     this.length = 0;
     this.prefix = "hashmap_prefix_20050524_";
}
/**
* 向HashMap中添加键值对
*/
HashMap.prototype.put = function (key, value)
{
     this[this.prefix + key] = value;
     this.length ++;
};
/**
* 从HashMap中获取value值
*/
HashMap.prototype.get = function(key)
{
     return typeof this[this.prefix + key] == "undefined" 
             ? null : this[this.prefix + key];
};
/**
* 从HashMap中获取所有key的集合，以数组形式返回
*/
HashMap.prototype.keySet = function()
{
     var arrKeySet = new Array();
     var index = 0;
     for(var strKey in this)
     {
    	
         if(strKey.substring(0,this.prefix.length) == this.prefix)
             arrKeySet[index ++] = strKey.substring(this.prefix.length);
     }
     return arrKeySet.length == 0 ? null : arrKeySet;
};
/**
* 从HashMap中获取value的集合，以数组形式返回
*/
HashMap.prototype.values = function()
{
     var arrValues = new Array();
     var index = 0;
     for(var strKey in this)
     {
         if(strKey.substring(0,this.prefix.length) == this.prefix)
             arrValues[index ++] = this[strKey];
     }
     return arrValues.length == 0 ? null : arrValues;
};
/**
* 获取HashMap的value值数量
*/
HashMap.prototype.size = function()
{
     return this.length;
};
/**
* 删除指定的值
*/
HashMap.prototype.remove = function(key)
{
     delete this[this.prefix + key];
     this.length --;
};
/**
* 清空HashMap
*/
HashMap.prototype.clear = function()
{
     for(var strKey in this)
     {
         if(strKey.substring(0,this.prefix.length) == this.prefix)
             delete this[strKey];   
     }
     this.length = 0;
};
/**
* 判断HashMap是否为空
*/
HashMap.prototype.isEmpty = function()
{
     return this.length == 0;
};
/**
* 判断HashMap是否存在某个key
*/
HashMap.prototype.containsKey = function(key)
{
     for(var strKey in this)
     {
        if(strKey == this.prefix + key)
           return true;  
     }
     return false;
};
/**
* 判断HashMap是否存在某个value
*/
HashMap.prototype.containsValue = function(value)
{
     for(var strKey in this)
     {
        if(this[strKey] == value)
           return true;  
     }
     return false;
};
/**
* 把一个HashMap的值加入到另一个HashMap中，参数必须是HashMap
*/
HashMap.prototype.putAll = function(map)
{
     if(map == null)
         return;
     if(map.constructor != JHashMap)
         return;
     var arrKey = map.keySet();
     var arrValue = map.values();
     for(var i in arrKey)
        this.put(arrKey[i],arrValue[i]);
};
//toString
HashMap.prototype.toString = function()
{
     var str = "";
     for(var strKey in this)
     {
         if(strKey.substring(0,this.prefix.length) == this.prefix)
               str += strKey.substring(this.prefix.length) 
                   + " : " + this[strKey] + "\r\n";
     }
     return str;
};