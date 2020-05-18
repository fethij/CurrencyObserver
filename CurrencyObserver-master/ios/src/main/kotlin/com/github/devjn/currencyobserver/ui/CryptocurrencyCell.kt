package com.github.devjn.currencyobserver.ui


import apple.coregraphics.struct.CGRect
import apple.uikit.UIImageView
import apple.uikit.UITableViewCell
import apple.uikit.UITextView
import org.moe.natj.general.NatJ
import org.moe.natj.general.Pointer
import org.moe.natj.general.ann.*
import org.moe.natj.objc.ObjCRuntime
import org.moe.natj.objc.ann.ObjCClassName
import org.moe.natj.objc.ann.Selector

@Runtime(ObjCRuntime::class)
@ObjCClassName("CryptocurrencyCell")
@RegisterOnStartup
class CryptocurrencyCell protected constructor(peer: Pointer) : UITableViewCell(peer) {

    @Selector("init")
    external override fun init(): CryptocurrencyCell

    @Deprecated("")
    @Selector("initWithFrame:reuseIdentifier:")
    external override fun initWithFrameReuseIdentifier(@ByValue frame: CGRect, reuseIdentifier: String): CryptocurrencyCell

    @Selector("initWithStyle:reuseIdentifier:")
    external override fun initWithStyleReuseIdentifier(@NInt style: Long, reuseIdentifier: String): CryptocurrencyCell


    @Selector("titleText")
    external fun titleText(): UITextView?

    @Selector("priceText")
    external fun priceText(): UITextView?

    @Selector("iconImage")
    external fun iconImage(): UIImageView?


    @Selector("setIconImage:")
    external fun setIconImage_unsafe(value: UIImageView?)

    fun setIconImage(value: UIImageView?) {
        val __old = iconImage()
        if (value != null) {
            org.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value)
        }
        setIconImage_unsafe(value)
        if (__old != null) {
            org.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old)
        }
    }

    @Selector("setPriceText:")
    external fun setPriceText_unsafe(value: UITextView?)

    fun setPriceText(value: UITextView?) {
        val __old = priceText()
        if (value != null) {
            org.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value)
        }
        setPriceText_unsafe(value)
        if (__old != null) {
            org.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old)
        }
    }
    
    @Selector("setTitleText:")
    external fun setTitleText_unsafe(value: UITextView?)

    fun setTitleText(value: UITextView?) {
        val __old = titleText()
        if (value != null) {
            org.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value)
        }
        setTitleText_unsafe(value)
        if (__old != null) {
            org.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old)
        }
    }

    companion object {
        init {
            NatJ.register()
        }

        @Owned
        @Selector("alloc")
        external fun alloc(): CryptocurrencyCell
    }
}