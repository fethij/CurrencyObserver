package com.github.devjn.currencyobserver.ui


import apple.NSObject
import apple.foundation.*
import apple.uikit.UIImage
import org.moe.natj.c.ann.FunctionPtr
import org.moe.natj.general.NatJ
import org.moe.natj.general.Pointer
import org.moe.natj.general.ann.*
import org.moe.natj.general.ptr.VoidPtr
import org.moe.natj.objc.Class
import org.moe.natj.objc.ObjCRuntime
import org.moe.natj.objc.SEL
import org.moe.natj.objc.ann.Selector
import org.moe.natj.objc.map.ObjCObjectMapper
import org.moe.natj.general.ann.NInt
import apple.uikit.UITableView
import apple.uikit.UITableViewCell
import apple.uikit.UITableViewController
import com.github.devjn.currencyobserver.rest.RestService
import com.github.devjn.currencyobserver.rest.data.CurrencyResponse
import com.github.devjn.currencyobserver.ui.CryptocurrencyTableViewController.Companion.CELL_IDENTIFIER
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.moe.UI
import kotlinx.coroutines.experimental.runBlocking
import org.moe.bindings.category.UIImageViewExt
import org.moe.natj.objc.ann.ObjCClassName
import java.util.*


@Runtime(ObjCRuntime::class)
@ObjCClassName("CurrencyTableViewController")
@RegisterOnStartup
class CurrencyTableViewController protected constructor(peer: Pointer) : UITableViewController(peer) {

    @Generated
    @Selector("init")
    override external fun init(): CurrencyTableViewController

    @Generated
    @Selector("initWithCoder:")
    override external fun initWithCoder(aDecoder: NSCoder): CurrencyTableViewController

    @Generated
    @Selector("initWithNibName:bundle:")
    override external fun initWithNibNameBundle(nibNameOrNil: String, nibBundleOrNil: NSBundle): CurrencyTableViewController


    private val data = ArrayList<Pair<String, Double>>()


    override fun tableViewCellForRowAtIndexPath(tableView: UITableView, indexPath: NSIndexPath): UITableViewCell {
        val cell = tableView.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER, indexPath) as UITableViewCell
        val item = data.get(indexPath.item().toInt())
        val text = "1 EUR = ${item.second} ${item.first}"
        cell.textLabel().setText(text)
        val url = getImageUrl(item.first)
//        println("CurrencyFragment " + "url = " + url)
        UIImageViewExt.sd_setImageWithURLPlaceholderImage(cell.imageView(), url,  UIImage.imageNamed("Currency"))
        return cell
    }

    override fun tableViewNumberOfRowsInSection(tableView: UITableView?, section: Long): Long {
        println("CurrencyFragment " + "data.size.toLong() = " + data.size.toLong())
        return data.size.toLong()
    }

    override fun numberOfSectionsInTableView(tableView: UITableView?): Long {
        return 1
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        println("viewDidLoad")
        doRequest()
    }

    private fun getImageUrl(name: String) = NSURL.URLWithString("https://www.xe.com/themes/xe/images/flags/${name.toLowerCase(Locale.US)}.png")

    fun doRequest() = runBlocking {
        val apiService = RestService.getCurrencyService()
        val result = apiService.getCurrency().await()
        println("CurrencyFragment " + "doRequest")
//        NSOperationQueue.mainQueue().addOperationWithBlock {
//            ad(result)
//        }
        ad(result)
    }

    fun ad(result: CurrencyResponse) {
        println("CurrencyFragment " + "Result = " + result)

        val list = result.rates!!.entries.map { Pair(it.key, it.value) }

        updateData(list)
    }

    protected fun updateData(result: List<Pair<String, Double>>) {
        println("CurrencyFragment " + "updateData = " + result)
        data.clear()
        data.addAll(result)
        NSOperationQueue.mainQueue().addOperationWithBlock {
            tableView().reloadData()
        }
    }


    companion object {
        const val CELL_IDENTIFIER = "currencyCell"

        init {
            NatJ.register()
        }

        @Generated
        @Selector("accessInstanceVariablesDirectly")
        external fun accessInstanceVariablesDirectly(): Boolean

        @Generated
        @Owned
        @Selector("alloc")
        external fun alloc(): CurrencyTableViewController

        @Generated
        @Selector("allocWithZone:")
        @MappedReturn(ObjCObjectMapper::class)
        external fun allocWithZone(zone: VoidPtr): Any

        @Generated
        @Selector("attemptRotationToDeviceOrientation")
        external fun attemptRotationToDeviceOrientation()

        @Generated
        @Selector("automaticallyNotifiesObserversForKey:")
        external fun automaticallyNotifiesObserversForKey(key: String): Boolean

        @Generated
        @Selector("cancelPreviousPerformRequestsWithTarget:")
        external fun cancelPreviousPerformRequestsWithTarget(@Mapped(ObjCObjectMapper::class) aTarget: Any)

        @Generated
        @Selector("cancelPreviousPerformRequestsWithTarget:selector:object:")
        external fun cancelPreviousPerformRequestsWithTargetSelectorObject(
                @Mapped(ObjCObjectMapper::class) aTarget: Any, aSelector: SEL,
                @Mapped(ObjCObjectMapper::class) anArgument: Any)

        @Generated
        @Selector("classFallbacksForKeyedArchiver")
        external fun classFallbacksForKeyedArchiver(): NSArray<String>

        @Generated
        @Selector("classForKeyedUnarchiver")
        external fun classForKeyedUnarchiver(): Class

        @Generated
        @Selector("clearTextInputContextIdentifier:")
        external fun clearTextInputContextIdentifier(identifier: String)

        @Generated
        @Selector("debugDescription")
        external fun debugDescription_static(): String

        @Generated
        @Selector("description")
        external fun description_static(): String

        @Generated
        @Selector("hash")
        @NUInt
        external fun hash_static(): Long

        @Generated
        @Selector("instanceMethodForSelector:")
        @FunctionPtr(name = "call_instanceMethodForSelector_ret")
        external fun instanceMethodForSelector(aSelector: SEL): NSObject.Function_instanceMethodForSelector_ret

        @Generated
        @Selector("instanceMethodSignatureForSelector:")
        external fun instanceMethodSignatureForSelector(aSelector: SEL): NSMethodSignature

        @Generated
        @Selector("instancesRespondToSelector:")
        external fun instancesRespondToSelector(aSelector: SEL): Boolean

        @Generated
        @Selector("isSubclassOfClass:")
        external fun isSubclassOfClass(aClass: Class): Boolean

        @Generated
        @Selector("keyPathsForValuesAffectingValueForKey:")
        external fun keyPathsForValuesAffectingValueForKey(
                key: String): NSSet<String>

        @Generated
        @Owned
        @Selector("new")
        @MappedReturn(ObjCObjectMapper::class)
        external fun new_objc(): Any

        @Generated
        @Selector("resolveClassMethod:")
        external fun resolveClassMethod(sel: SEL): Boolean

        @Generated
        @Selector("resolveInstanceMethod:")
        external fun resolveInstanceMethod(sel: SEL): Boolean

        @Generated
        @Selector("setVersion:")
        external fun setVersion(@NInt aVersion: Long)

        @Generated
        @Selector("superclass")
        external fun superclass_static(): Class

        @Generated
        @Selector("version")
        @NInt
        external fun version_static(): Long
    }
}