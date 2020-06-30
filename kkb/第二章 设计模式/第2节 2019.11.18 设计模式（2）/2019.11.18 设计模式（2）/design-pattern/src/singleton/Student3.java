package singleton;
/**
 * 瀵筭etSingletonInstance鏂规硶鍔爏ynchronized鍏抽敭瀛�
 * 
 * 
 * synchronized鍏抽敭瀛楅攣浣忕殑鏄繖涓璞★紝杩欐牱鐨勭敤娉曪紝鍦ㄦ�ц兘涓婁細鏈夋墍涓嬮檷
 * 鍥犱负姣忔璋冪敤getInstance()锛岄兘瑕佸瀵硅薄涓婇攣
 * 浜嬪疄涓婏紝鍙湁鍦ㄧ涓�娆″垱寤哄璞＄殑鏃跺�欓渶瑕佸姞閿侊紝涔嬪悗灏变笉闇�瑕佷簡
 * 鎵�浠ワ紝杩欎釜鍦版柟闇�瑕佹敼杩�
 * 
 * @author 鎬″惥瀹�
 *
 */
public class Student3 {

	private Student3(){};
	
	private static Student3 student = null;
	
	// 姝ゅ鑰冮獙瀵箂ynchronized鐭ヨ瘑鐐圭殑鎺屾彙鎯呭喌
	public static synchronized Student3 getSingletonInstance(){
		if (student == null) {
			student = new Student3();
		}
		return student;
	}
	
}
