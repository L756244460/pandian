package cn.datasset.yipandian.client.code;

/**
 * @author lzl
 */
public enum  ErrorCode {
    /**
     * 错误码
     */
    SUCCESS(200, "成功"),
    FAIL(100, "操作失败"),

    ALLOW_LIST_NO_APPLICATION_PERMISSION(101, "您没有应用权限，请联系管理员！"),

    ALLOW_LIST_NO_POSITION_PERMISSION(102, "您所属岗位无权限访问，请联系管理员！"),

    ALLOW_LIST_APPLICATION_EXPIRED(103, "应用权限已过期，请联系客服开通！"),

    ALLOW_LIST_APPLICATION_ABOUT_TO_EXPIRE(104, "90天后应用将逾期，请联系客服！"),

    NO_MENU_AUTH(201, "您所属岗位无权限访问，请联系管理员！"),

    REPEAT(301, "数据重复"),
    NAME_REPEAT(301, "名称重复"),
    NAME_REPEAT_ROLE(301, "角色名称重复"),
    PARAM_NULL(302, "参数为空"),
    PARAM_NULL_ORGID(302, "单位ID为空!"),
    PARAM_NULL_TENANT(302, "租户信息为空!"),
    PARAM_NULL_ZICHANBH(302, "资产编号参数为空!"),
    PARAM_NULL_ROLE(302, "角色参数为空"),
    REQUIRED_PARAM_NULL(302, "必传参数为空"),
    IMG_URL_NULL(302, "图片地址为空"),
    STOCK_DIMENSION_NULL(302, "指定盘点时, 盘点维度必传"),
    PARAM_ERROR(303, "参数有误"),
    DATA_EMPTY(304, "无数据"),
    DATA_EMPTY_ZICHAN(304, "无资产数据!"),
    DATA_EMPTY_ASSET_SUBSCRIBE(304, "无订阅资产"),
    CODE_ARRAY_EMPTY(304, "指定盘点时, 分类编码或部门编码数组不能为空"),
    STOCK_ASSET_EMPTY(304, "当前盘点范围无资产"),
    LEVEL_OVERFLOW(305, "当前层级已是最末级"),
    FIELD_OVERFLOW(306, "字段配置最多支持8个"),
    NOT_CUSTOM(306, "当前修改的类别非自定义类别"),
    DATA_EMPTY_FOR_TEMPLATE(330, "解析模板数据为空"),
    USER_INVALID(401, "用户信息无效"),
    USER_LOCKED(402, "用户冻结中"),
    USER_BLACK(403, "用户处于黑名单中"),
    ADD_ERROR(411, "保存失败"),
    ADD_ERROR_DOCUMENT(411, "单据提交失败!"),
    ADD_ERROR_ROlE(411, "角色保存失败"),
    ADD_NOT_CUSTOM_FAIL(411, "财政端所属类别只能在末级添加"),
    START_TASK_FAIL(411, "任务启动失败"),
    UPDATE_ERROR(411, "更新失败"),
    UPDATE_ERROR_PROCESS_INCOMPLETE(411, "流程资源不完整，无法启用!"),
    UPDATE_ERROR_MODEL_USED(411, "流程模板启用中, 更新失败"),
    UPDATE_STATUS_ILLEGAL(411, "当前任务状态不允许更新"),
    UPDATE_STATUS_NOT_ALLOWED(411, "当前任务已完成或已终止,不允许再次操作!"),
    UPDATE_INVENTORY_FAIL(411, "全部盘存失败"),
    UPDATE_BATCH_STATUS_ILLEGAL(411, "当前资产的状态操作非法!"),
    UPDATE_ERROR_ONLINE(411, "线上状态，更新失败"),
    REMOVE_ERROR(413, "删除失败！"),
    REMOVE_INVALID(413, "无效删除"),
    REMOVE_ILLEGAL(413, "当前任务不允许删除!"),
    REMOVE_ERROR_CUSTOM(413, "删除失败,类别不是自定义的类别！"),
    REMOVE_ERROR_CATEGORY(413, "该类别下存在子类别，无法删除"),
    LOGIN_PARAM_NULL(414, "用户名或密码不得为空"),
    LOGIN_INCORRECT(416, "用户名或密码不正确"),
    REGISTER_NOTNULL(445, "该账号已被创建"),
    NUMBER_NOTLOGIN(446, "账号已失效"),
    LOGIN_TOKEN_INVALID(417, "token无效"),
    SYNC_FAILED(415, "同步失败!"),
    SYNC_FAILED_ASSET_REGISTER(415, "资产登记至财政端失败!"),
    SYNC_FAILED_ASSETCARDLIST(415, "从资产云获取资产卡片列表失败!"),
    PERSON_PIC_ERROR(417, "图片上传失败"),
    PERSON_INVALID(419, "获取用户信息失败"),
    AUTH_TOKEN_NULL(421, "令牌无效"),
    AUTH_TOKEN_UNVALID(422, "登录已过期"),
    CHECK_CODE_EXPIRED(422, "验证码已过期"),
    CHECK_CODE_ERROR(423, "验证码错误"),
    WX_AUTH_ERROR(423, "微信令牌请求失败！"),
    WX_NOT_AUTHORIZE(424, "微信未注册"),
    WX_AUTHORIED_OTHERS(425, "手机号已被其他微信注册"),
    WX_USER_STATUS_ERROR(426, "该账号已被停用!"),
    PROPERTCLOUD_CARD_EXCEPTION(427, "资产云卡片调用失败"),
    PHONE_NUMBER_ILLEGAL(428, "手机号不合法"),
    TOKEN_ILLEGAL(429, "token不合法"),
    PASSWORD_INCORRECT(440, "原密码错误"),
    CONFIRM_PASSWORD_INCORRECT(441, "两次输入的密码不一致"),
    RUNTIME_EXCEPTION(500, "服务异常！请联系系统管理员。"),
    REQUEST_ERROR(501, "请求错误！"),
    UPLOAD_AUDIO_ERROR(502, "语音上传失败！"),
    UPLOAD_PIC_ERROR(503, "图片上传失败！"),
    SERIAL_NULL(507, "无此记录！"),

    EXISTS_CODE(508, "存在该名称记录"),

    EXPORT_SUCESS(600, "导出成功"),
    EXPORT_FAIL(601, "导出失败"),
    IMPORT_SUCESS(602, "导入成功"),
    IMPORT_FAIL(603, "导入失败"),
    DOWNLOAD_SUCESS(604, "下载成功"),
    DOWNLOAD_FAIL(605, "下载失败"),
    RESPONSE_PACK_ERROR(700, "统一response包装失败"),

    ALLOWLIST_APP_EXISTS(800, "应用已存在，不能重复添加"),
    NOT_LOGIN(1005, "尚未登陆"),
    CONNOT_GET_TOKEN(1006, "无法获取到token"),
    COMMON_ERROR(-1, "通用错误"),
    NO_PERMISSION(1007, "没有权限"),
    NO_WRITE_PERMISSION(1007, "缺少数据操作权限"),
    ADMIN_NO_DEPART(1007, "当前部门管理员没有分配部门"),
    DEPT_NO_PERMISSION(1007, "当前用户角色没有权限查看其它部门,请重新选择!"),
    ASSETS_CLOUD_ERROR(1008, "资产云相关服务异常"),
    CONFIGURATION_APPROVAL_NULL_ERROR(1009, "审批配置为空，请设置审批配置"),
    APPROVAL_PROCESS_MODEL_CONFIG_NULL(1009, "当前业务审批流程模板为空! 请先配置模板"),
    OTHER_INTERFACE_FAIL(1010, "第三方接口调用失败"),
    APPROVAL_PROCESS_NOT_COMPLETED(1009, "审批流程条件不满足! 请先配置模板"),
    APPROVAL_PROCESS_NOT_USER_TASK(1009, "审批流程没有用户审批任务!请先配置模板"),
    APPROVAL_PROCESS_TASK_NO_APPROVAL_USER(1010, "当前业务审批任务没有审批人!"),
    APPROVAL_TASK_STATUS_ERROR(1011, "审批任务状态错误!"),
    APPROVAL_TASK_SET_STATUS_ERROR(1011, "审批任务设置审批状态异常!"),
    APPROVAL_TASK_HAS_COMPLETED(1012, "当前审批节点任务已完成,单据已流转到下一审批节点!"),
    APPROVAL_PROCESS_HAS_NOT_DOCUMENT_NUMBER(1013, "当前单据没有提交至审批流程!"),
    APPROVAL_TASK_HAS_SUBMITTED(1014, "当前单据已提交"),

    FLOWABLE_NOT_FOUND_OBJECT(1050, "流程实体对象无法找到"),

    PROVIDE_CATEGORY_ASSIGN_SPLIT(1101, "周期发放管理拆分资产异常"),
    PROVIDE_CATEGORY_ASSIGN_DISTRIBUTE(1102, "周期发放管理分配资产异常"),
    PROVIDE_CATEGORY_EXPORT_NO_CATEGORY(1110,"请在资产范围管理内添加资产分类"),
    PROVIDE_CATEGORY_OVERFLOW(1111, "发放规则不能超过200个"),

    PROVIDE_CATEGORY_EXPORT_NO_USER(1110,"不存在人员，请添加人员或者修改搜索条件"),
    CATEGORY_IS_EMPTY(1120, "资产分类为空"),
    CATEGORY_NOT_VALID(1121, "无效的资产分类"),
    TEMPLATE_HAS_BINDING(1140, "模版已经绑定分类，请取消绑定后删除"),
    DING_TALK_AUTH_ERROR(1400, "钉钉接口请求失败"),
    DING_TALK_NOT_ALLOW(1500, "钉钉免登用户禁止操作"),

    ;

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
