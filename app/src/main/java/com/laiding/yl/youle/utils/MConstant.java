package com.laiding.yl.youle.utils;

import android.text.TextUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by JunChen on 2018/3/5.
 * Remarks
 */

public class MConstant {

    public static final String DIETASSISTANT = "http://m.51laiding.xyz/yszs/index.html"; //饮食助手

    public static final String TESTTUBEASSISTANT = "http://m.51laiding.xyz/sgzs/index.html";//试管助手

    public static final String Can = "http://m.51laiding.xyz/nbnz/index.html";//能不能做

    public static final String SURROGATEPROCESS = "http://m.51laiding.xyz/dylc/index.html";//代孕流程

    public static final String HEFADAIYUN = "http://m.51laiding.xyz/hfdy/index.html";//合法代孕

    public static final String DAIYUNZIDAO = "http://m.51laiding.xyz/dyzd/index.html";//代孕指导

    public static final String RECORDIMG = "http://m.51laiding.xyz/photo/";//诊疗记录详情IMG


    //子宫问题：
    //宫外孕：
    public static final String GWY = "“宫外孕”以输卵管妊娠最常见。病因常由于输卵管管腔或周围的炎症。输卵管妊娠在流产或破裂前往往无明显症状，也可有停经、腹痛、少量阴道出血。破裂后表现为急性剧烈腹痛，反复发作，阴道出血，以至休克。治疗以手术为主。";
    public static final String ZGJX = "子宫畸形，又称子宫发育异常，是一种先天性疾患，也是生殖器官畸形中最常见的一种。有些子宫畸形患者可无任何自觉症状，月经、性生活、妊娠、分娩等亦均无异常表现，以至终身不被发现，或于体检时偶被发现。";
    public static final String ZGNMB = "子宫内膜薄女性常见病症，女性在有一定雌激素的作用下，做超声检查时子宫内膜不能达到8毫米的厚度就判断为子宫内膜薄。";
    public static final String ZGNXR = "子宫内膜息肉为炎性子宫内膜局部血管和结缔组织增生形成息肉状赘生物突入宫腔内所致，息肉大小数目不一，多位于宫体部，借助细长蒂附着于子宫腔内壁，主要表现为经期延长和经量增多。";
    public static final String ZGNZS = "子宫内膜增生症是由于雌激素增高引起的子宫内膜增生，表现为功能性子宫出血，育龄期和更年期妇女均可发病。子宫内膜增生、不典型增生和子宫内膜癌，无论是形态学还是生物学都为一连续的演变过程。";
    public static final String ZGJL = "子宫肌瘤是女性生殖器官中最常见的一种良性肿瘤，也是人体中最常见的肿瘤之一，又称为纤维肌瘤、子宫纤维瘤。";
    public static final String GGLM = "常见于行人工流产术或自然流产刮宫术后。以及产后出血刮宫术后。子宫内膜基底层被刮掉，产生术后宫腔粘连；非妊娠引起的宫腔粘连如子宫内膜结核、子宫肌瘤挖除术，诊断性刮宫术等。";
    //卵巢问题：
    public static final String LCFYBUHAO = "卵巢发育不良是一种先天性染色体异常所致的疾病。卵巢发育不良会导致闭经、乳房不发育等症状。女性患卵巢发育不良就会导致不孕。";
    public static final String DUORANGLUANCHAO = "多囊卵巢综合征是育龄妇女较常见的内分泌症候群。并非一种独特的疾病，而是一种多病因、表现极不均一的临床综合征。";
    public static final String LUANCHAOZAOSUAI = "卵巢早衰是指卵巢功能衰竭所导致的40岁之前即闭经的现象。特点是原发或继发闭经伴随血促性腺激素水平升高和雌激素水平降低，并伴有不同程度的一系列低雌激素症状如:潮热多汗、面部潮红、性欲低下等。";
    public static final String LUANCHAORANGZ = "卵巢肿瘤是女性生殖器常见肿瘤，有各种不同的性质和形态，即一侧性或双侧性、囊性或实性、良性或恶性，其中以囊性多见，有一定的恶性比例。";
    public static final String NYQR = "卵巢肿瘤是女性生殖器常见肿瘤，有各种不同的性质和形态，即一侧性或双侧性、囊性或实性、良性或恶性，其中以囊性多见，有一定的恶性比例。";

    //流产问题
    public static final String DCTTLC = "胎停育是指胚胎发育到一个阶段发生了死亡而停止继续发育的现象。胚胎停育是自然流产前的阶段，胚胎停育后如果被母体自我保护性的排出体外称之为自然流产，如果排出失败而残留在宫腔内称之为稽留流产。";
    public static final String ZIRANLIUCHAN = "自然流产是指妊娠在28周前自行终止，胎儿体重＜1000g者。自然流产如果处理不及时，可能遗留生殖器官炎症、损伤，或因大出血危及孕妇健康，甚至威胁生命。";
    public static final String XIANZHAOLIUCHAN = "先兆流产指妊娠28周前，出现少量阴道道流血和(或)下腹疼痛，宫口未开，胎膜未破，妊娠物尙未排出，子宫大小与停经周数相符者。从民间传统的说法上讲，先兆流产的主要依据就是“见红”。";
    public static final String XGXLC = "习惯性流产为自然流产连续３次以上者，每次流产往往发生在同一妊娠月份。原因大多为孕妇黄体功能不全、甲状腺功能低下、先天性子宫畸形、子宫发育异常、宫腔粘连、子宫肌瘤、染色体异常、自身免疫等。";
    public static final String SHENGHUARENCHENG = "生化妊娠就是精卵结合了，但是没有回到子宫里着床，或者是回来了，没有着上床。生化妊娠属于亚临床流产。如果生化妊娠偶尔一次，不会有大的影响。转经后就可以恢复正常，不影响以后怀孕。";
    //输卵管问题
    public static final String SLGJY = "是指输卵管受病原体感染以后，由于白细胞的浸润形成内膜肿胀、间质水肿、渗出，输卵管粘膜上皮脱落，如果输卵管急性期炎症没得到及时有效的治疗就形成了输卵管积脓。";
    public static final String SLGTRBC = "输卵管通而不畅是一种疾病。输卵管是一对喇叭状弯曲的长管，左右各一条，长约8-15厘米。根据其形态可分为四部分。";

    //内分泌
    public static final String NEIFENGMI = "激素六项检测即是女性常规的生殖系统常规检查，即卵泡生成激素(FSH)、黄体生成激素(LH)、雌二醇 (E2)、孕酮(P)、睾酮(T)、催乳激素(PRL)。各实验室所得结果不完全相同。";

    //男士问题
    public static final String RUOJING = "弱精子症又称精子活力低下。如果附睾等器官和附性腺发生了感染性疾病、免疫方面的疾病睾丸自身功能与发育方面的疾病等等，都足以导致精子向前运动能力减弱或丧失。";
    public static final String QIANLIEXIANYAN = "前列腺炎是泌尿男性生殖系统的常见病。分为4类：急性细菌性前列腺炎、慢性细菌性前列腺炎、慢性非细菌性前列腺炎、前列腺痛。";
    public static final String JINGZIJIXING = "精子畸形也叫畸形精子，是指头、体、尾的形态变异，头部畸形有巨大头、无定形、双头等;体部畸形有体部粗大、折裂、不完整等;尾部畸形有卷尾、双尾、缺尾等。";
    public static final String SHAOJING = "少精症的主要原因有:精索静脉曲张、免疫因素、染色体异常、隐睾疾病、生殖道感染、内分泌疾病等。少精症是男性不育的常见原因，中医治疗少精症效果较满意。";
    public static final String SIJING = "死精症是指精子成活率减少，死精子超过40%者，本病为男子不育原因之一。";
    public static final String LENGDONGLUANZI = "冷冻卵子，即取母体健康时的卵子冷冻，阻止卵子随人体衰老，待想生育时取出冷冻的卵子使用即可。还没有生育计划的女性，可以通过冷冻卵子的方式保存年轻状态，提高受孕率。";
    public static final String JZBYH = "精液不液化，指精液排出体外后超过30分钟仍呈胶冻状。常见的原因是精囊炎和前列腺炎所致前列腺分泌的纤维蛋白溶解酶不足；微量元素（镁，锌等）缺乏；先天性前列腺缺如等。";

    //其他
    public static final String ERTAIBEIYU = "二胎妈妈经验充足较头胎宝宝更易生育，不过许多妈妈却是大龄二胎，对身体要求极高，孕期、生产均处于高危状态，二胎备孕期间更应该注意身体状况，进行有效的治疗与休养。";
    public static final String SHENGNANSHENGNV = "生男生女一直是很多家庭头疼的事情，对于现代人的心理需求，医学技术也在不断发展进步，虽然第三代试管婴儿技术能够识别性别，但我国并不允许自主选择孩子性别。";
    public static final String FUMEISHENGZI = "美国的宪法规定任何人只要在美国领土出生都算美国人，这催生越来越多外国人选在美国生产，以让孩子获得美国国籍。";
    public static final String HEFADAIYUNL = "自国家删除《辅助生殖办法》中关于明确禁止医疗机构和医务人员进行代孕条例后，代孕合法化问题一直成为国人关心热点，代孕在中国已有二十多年历史。代孕合法化也会逐步进行中。";
    public static final String HEFAGONGLUAN = "考虑到部分女性的实际需求，国家并没有禁止“供卵”，而是制定了相当严格的规定。";




    /**
     *  文件上传
     * @param files
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*;charset=utf-8"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("img[]", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    /**
            * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
             * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

}
