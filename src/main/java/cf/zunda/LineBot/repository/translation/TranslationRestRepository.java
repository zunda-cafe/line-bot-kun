package cf.zunda.LineBot.repository.translation;

import cf.zunda.LineBot.model.TranslationMessage;

/**
 * Description: Microsoft Translation API を扱うWebService．
 * @author ykonno.
 * @since 2017/06/09.
 */
public interface TranslationRestRepository {

    /** TranslationAPIに翻訳対象文字列を投げる */
    public String translate(TranslationMessage translationMessage);
}
