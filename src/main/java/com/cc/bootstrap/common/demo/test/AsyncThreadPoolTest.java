package com.cc.bootstrap.common.demo.test;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 使用异步线程池
 * @author: ChenChen
 * @date: 2022/3/29 9:14
 */
@RestController
@RequestMapping(value = "/test/aysnc")
public class AsyncThreadPoolTest {
    private static Logger LOGGER = LoggerFactory.getLogger(AsyncThreadPoolTest.class);

    @Autowired
    private AsyncThreadPoolService asyncThreadPoolService;

//    // 不能再本类中使用，否则不会有异步效果
//    @Async("asyncThreadPool")
//    public void asyncThreadPoolTest(String printPrefix) {
//        LOGGER.info(">>>>>>>>>>>>>>异步打印开始<<<<<<<<<<<<<");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 1000; i++) {
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            stringBuilder.append(printPrefix + i);
//        }
//        System.out.println(stringBuilder);
//        LOGGER.info(">>>>>>>>>>>>>>异步打印结束<<<<<<<<<<<<<");
//    }

    // get http://localhost:8013/test/aysnc/print?printPrefix=cc
    @GetMapping(value = "/print")
    public ResponseResult print(@RequestParam(name = "printPrefix") String printPrefix) {
        LOGGER.info(">>>>>>>>>>>>>>开始测试异步打印请求<<<<<<<<<<<<<");
        asyncThreadPoolService.asyncThreadPoolTest(printPrefix);
        LOGGER.info(">>>>>>>>>>>>>>测试异步打印请求结束<<<<<<<<<<<<<");
        return ResponseResult.success();
    }
    //日志打印效果，异步测试成功。
//09:44:09.073 [http-nio-8013-exec-1] INFO  AsyncThreadPoolTest ->>>>>>>>>>>>>>开始测试异步打印请求<<<<<<<<<<<<<
//09:44:09.077 [http-nio-8013-exec-1] INFO  AsyncThreadPoolTest ->>>>>>>>>>>>>>测试异步打印请求结束<<<<<<<<<<<<<
//09:44:09.081 [single-run-test-1] INFO  AsyncThreadPoolService ->>>>>>>>>>>>>>异步打印开始<<<<<<<<<<<<<
//            cc0cc1cc2cc3cc4cc5cc6cc7cc8cc9cc10cc11cc12cc13cc14cc15cc16cc17cc18cc19cc20cc21cc22cc23cc24cc25cc26cc27cc28cc29cc30cc31cc32cc33cc34cc35cc36cc37cc38cc39cc40cc41cc42cc43cc44cc45cc46cc47cc48cc49cc50cc51cc52cc53cc54cc55cc56cc57cc58cc59cc60cc61cc62cc63cc64cc65cc66cc67cc68cc69cc70cc71cc72cc73cc74cc75cc76cc77cc78cc79cc80cc81cc82cc83cc84cc85cc86cc87cc88cc89cc90cc91cc92cc93cc94cc95cc96cc97cc98cc99cc100cc101cc102cc103cc104cc105cc106cc107cc108cc109cc110cc111cc112cc113cc114cc115cc116cc117cc118cc119cc120cc121cc122cc123cc124cc125cc126cc127cc128cc129cc130cc131cc132cc133cc134cc135cc136cc137cc138cc139cc140cc141cc142cc143cc144cc145cc146cc147cc148cc149cc150cc151cc152cc153cc154cc155cc156cc157cc158cc159cc160cc161cc162cc163cc164cc165cc166cc167cc168cc169cc170cc171cc172cc173cc174cc175cc176cc177cc178cc179cc180cc181cc182cc183cc184cc185cc186cc187cc188cc189cc190cc191cc192cc193cc194cc195cc196cc197cc198cc199cc200cc201cc202cc203cc204cc205cc206cc207cc208cc209cc210cc211cc212cc213cc214cc215cc216cc217cc218cc219cc220cc221cc222cc223cc224cc225cc226cc227cc228cc229cc230cc231cc232cc233cc234cc235cc236cc237cc238cc239cc240cc241cc242cc243cc244cc245cc246cc247cc248cc249cc250cc251cc252cc253cc254cc255cc256cc257cc258cc259cc260cc261cc262cc263cc264cc265cc266cc267cc268cc269cc270cc271cc272cc273cc274cc275cc276cc277cc278cc279cc280cc281cc282cc283cc284cc285cc286cc287cc288cc289cc290cc291cc292cc293cc294cc295cc296cc297cc298cc299cc300cc301cc302cc303cc304cc305cc306cc307cc308cc309cc310cc311cc312cc313cc314cc315cc316cc317cc318cc319cc320cc321cc322cc323cc324cc325cc326cc327cc328cc329cc330cc331cc332cc333cc334cc335cc336cc337cc338cc339cc340cc341cc342cc343cc344cc345cc346cc347cc348cc349cc350cc351cc352cc353cc354cc355cc356cc357cc358cc359cc360cc361cc362cc363cc364cc365cc366cc367cc368cc369cc370cc371cc372cc373cc374cc375cc376cc377cc378cc379cc380cc381cc382cc383cc384cc385cc386cc387cc388cc389cc390cc391cc392cc393cc394cc395cc396cc397cc398cc399cc400cc401cc402cc403cc404cc405cc406cc407cc408cc409cc410cc411cc412cc413cc414cc415cc416cc417cc418cc419cc420cc421cc422cc423cc424cc425cc426cc427cc428cc429cc430cc431cc432cc433cc434cc435cc436cc437cc438cc439cc440cc441cc442cc443cc444cc445cc446cc447cc448cc449cc450cc451cc452cc453cc454cc455cc456cc457cc458cc459cc460cc461cc462cc463cc464cc465cc466cc467cc468cc469cc470cc471cc472cc473cc474cc475cc476cc477cc478cc479cc480cc481cc482cc483cc484cc485cc486cc487cc488cc489cc490cc491cc492cc493cc494cc495cc496cc497cc498cc499cc500cc501cc502cc503cc504cc505cc506cc507cc508cc509cc510cc511cc512cc513cc514cc515cc516cc517cc518cc519cc520cc521cc522cc523cc524cc525cc526cc527cc528cc529cc530cc531cc532cc533cc534cc535cc536cc537cc538cc539cc540cc541cc542cc543cc544cc545cc546cc547cc548cc549cc550cc551cc552cc553cc554cc555cc556cc557cc558cc559cc560cc561cc562cc563cc564cc565cc566cc567cc568cc569cc570cc571cc572cc573cc574cc575cc576cc577cc578cc579cc580cc581cc582cc583cc584cc585cc586cc587cc588cc589cc590cc591cc592cc593cc594cc595cc596cc597cc598cc599cc600cc601cc602cc603cc604cc605cc606cc607cc608cc609cc610cc611cc612cc613cc614cc615cc616cc617cc618cc619cc620cc621cc622cc623cc624cc625cc626cc627cc628cc629cc630cc631cc632cc633cc634cc635cc636cc637cc638cc639cc640cc641cc642cc643cc644cc645cc646cc647cc648cc649cc650cc651cc652cc653cc654cc655cc656cc657cc658cc659cc660cc661cc662cc663cc664cc665cc666cc667cc668cc669cc670cc671cc672cc673cc674cc675cc676cc677cc678cc679cc680cc681cc682cc683cc684cc685cc686cc687cc688cc689cc690cc691cc692cc693cc694cc695cc696cc697cc698cc699cc700cc701cc702cc703cc704cc705cc706cc707cc708cc709cc710cc711cc712cc713cc714cc715cc716cc717cc718cc719cc720cc721cc722cc723cc724cc725cc726cc727cc728cc729cc730cc731cc732cc733cc734cc735cc736cc737cc738cc739cc740cc741cc742cc743cc744cc745cc746cc747cc748cc749cc750cc751cc752cc753cc754cc755cc756cc757cc758cc759cc760cc761cc762cc763cc764cc765cc766cc767cc768cc769cc770cc771cc772cc773cc774cc775cc776cc777cc778cc779cc780cc781cc782cc783cc784cc785cc786cc787cc788cc789cc790cc791cc792cc793cc794cc795cc796cc797cc798cc799cc800cc801cc802cc803cc804cc805cc806cc807cc808cc809cc810cc811cc812cc813cc814cc815cc816cc817cc818cc819cc820cc821cc822cc823cc824cc825cc826cc827cc828cc829cc830cc831cc832cc833cc834cc835cc836cc837cc838cc839cc840cc841cc842cc843cc844cc845cc846cc847cc848cc849cc850cc851cc852cc853cc854cc855cc856cc857cc858cc859cc860cc861cc862cc863cc864cc865cc866cc867cc868cc869cc870cc871cc872cc873cc874cc875cc876cc877cc878cc879cc880cc881cc882cc883cc884cc885cc886cc887cc888cc889cc890cc891cc892cc893cc894cc895cc896cc897cc898cc899cc900cc901cc902cc903cc904cc905cc906cc907cc908cc909cc910cc911cc912cc913cc914cc915cc916cc917cc918cc919cc920cc921cc922cc923cc924cc925cc926cc927cc928cc929cc930cc931cc932cc933cc934cc935cc936cc937cc938cc939cc940cc941cc942cc943cc944cc945cc946cc947cc948cc949cc950cc951cc952cc953cc954cc955cc956cc957cc958cc959cc960cc961cc962cc963cc964cc965cc966cc967cc968cc969cc970cc971cc972cc973cc974cc975cc976cc977cc978cc979cc980cc981cc982cc983cc984cc985cc986cc987cc988cc989cc990cc991cc992cc993cc994cc995cc996cc997cc998cc999
//09:44:09.081 [single-run-test-1] INFO  AsyncThreadPoolService ->>>>>>>>>>>>>>异步打印结束<<<<<<<<<<<<<
}
