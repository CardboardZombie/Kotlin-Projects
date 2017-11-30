package com.cybertiger.tadhg.cybertigersolutions

/**
 * Created by Tadhg on 21/10/2017.
 */
class Experience{
    var ic:Int?=null
    var n:String?=null
    var sn:String?=null
    var ds:String?= null
    var d:String?=null
    var i:Int?=null
    var t:Int?=null
    var tL:String?=null
    var rd:String?=null
    var re:String?=null

    //Version 1 - Needed information for Testing
    constructor(iconID:Int, name:String, subname:String, date:String, desc:String, imageID:Int, typeOf:Int){
        this.ic = iconID
        this.n = name
        this.sn = subname
        this.d = date
        this.ds = desc
        this.i = imageID
        this.t = typeOf
        this.tL = "Whoop Whoop"
        this.rd = "I did lots of work."
        this.re = "I faced lots of challenges."
    }

    //Version 2 - More information added.
    constructor(iconID:Int, name:String, subname:String, date:String, desc:String, imageID:Int, typeOf:Int, tagLine:String, roleDesc:String, roleExp:String){
        this.ic = iconID
        this.n = name
        this.sn = subname
        this.d = date
        this.ds = desc
        this.i = imageID
        this.t = typeOf
        this.tL = tagLine
        this.rd = roleDesc
        this.re = roleExp
    }
}