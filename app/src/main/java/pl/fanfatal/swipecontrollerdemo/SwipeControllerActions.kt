package pl.fanfatal.swipecontrollerdemo

abstract class SwipeControllerActions {

    fun onLeftClicked(position: Int) {}

    open fun onRightClicked(position: Int) {}
}
