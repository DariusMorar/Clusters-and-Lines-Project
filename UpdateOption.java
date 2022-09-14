
/**
 * enum class for update options
 * 
 * When the user sets cluster option or line option and click run, renderer passes list of options
 * by using this enum. Handlers then receive this options via the observable model singleton.
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
public enum UpdateOption {
    LINE, CLUSTER, BOTH
}