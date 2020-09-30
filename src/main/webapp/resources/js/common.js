(function(){
	/**
	 * 公共js库，封装常用js
	 * 调用直接：bi.xx()
	 */
    if(!window.bi){
        window.bi={}
    }
    window.bi={
        /**
         * toastr插件
         * @param mold:success,info,warning,error
         * @param title:标题
         * @param msg:提示信息
         */
        alert:function(mold,title,msg){
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "progressBar": false,
                "positionClass": "toast-top-center",
                "onclick": null,
                "showDuration": "400",
                "hideDuration": "1000",
                "timeOut": "7000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
            Command: toastr[mold](msg, title);
        },
        /**
         *layer插件
         * @param conent:标题
         * @param onSuccess:确定回调
         * @param onError:失败回调
         * @returns {*}
         */
        confirm:function(content,onSucess,onError){
            parent.layer.confirm(content, {submit: ['确定','取消'], shade: 0.4}, onSucess, onError);
        },
        /**
         * loading
         */
        showLoading:function(){
            var index = layer.load(2, {time: 5*1000,shade:0.1}); //0代表加载的风格，支持0-2
            return	index;
        },
        hideLoading:function(){
            layer.close(this.showLoading());
        }
    }
})();

function closeTab(){
	parent.closeThisPage(window.location.pathname+window.location.search);
}
