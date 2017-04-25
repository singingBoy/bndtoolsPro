package wds.commons.apis;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BaseUtil {
	/**
	 * 从json格式字符串反序列化实体
	 * 
	 * @param content
	 *            实体的json字符串
	 * @param valueType
	 *            实体类型
	 * @return 实体对象
	 * @throws Exception
	 */
	public static <T> T readValue(String content, Class<T> valueType) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
		// mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return mapper.readValue(content, valueType);
	}

	/**
	 * map反序列化实体
	 * 
	 * @param map
	 *            实体的map
	 * @param valueType
	 *            实体类型
	 * @return 实体对象
	 * @throws Exception
	 */

	public static <T> T readValue(Map<String, String> map, Class<T> valueType) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		return readValue(json, valueType);
	}

	/**
	 * 获取上传表单数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	/*public static RequesForm getRequesForm(HttpServletRequest request) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		RequesForm requesForm = new RequesForm();
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (!item.isFormField()) {
						InputStream ins = item.getInputStream();
						String name = item.getName();
						if (null != ins && null != name && !"".equals(name)) {
							Document doc = new Document(ins, name, item.getFieldName());
							requesForm.getDocs().add(doc);
						}
					} else if (item.isFormField()) {
						requesForm.getMap().put(item.getFieldName(), item.getString("UTF-8"));
					}
				}
			} catch (FileUploadException | IOException e) {
				throw new IOException("参数解析失败");
			}
		}
		return requesForm;
	}*/
	
	public static String transToTextarea(String s) {
        return "<html><body><textarea>" + s + "</textarea></body></html>";
    }
}
