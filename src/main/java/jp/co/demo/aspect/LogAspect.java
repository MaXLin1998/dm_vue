package jp.co.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /** コントローラーの実行前後にログを出力する */
    @Around("@within(*)")
    public Object startLog(@org.jetbrains.annotations.NotNull ProceedingJoinPoint jp) throws Throwable {

        // 開始ログの出力
        log.info("メソッドの開始：{}", jp.getSignature());

        try {
            //メソッドの実行
            Object result = jp.proceed();

            // メソッドの実行
            log.info("メソッドの終了：{}", jp.getSignature());

            // 実行結果を呼び出し元に返却
            return result;

        } catch(Exception e) {
            // エラーログ出力
            log.error("メソッド異常終了：{}", jp.getSignature());


            // エラーの再スロー
            throw e;
        }
    }
}
