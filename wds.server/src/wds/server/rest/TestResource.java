package wds.server.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import wds.commons.apis.Query.Condition;
import wds.commons.apis.QueryParams;
import wds.commons.apis.QueryUtil;
import wds.server.api.TestService;
import wds.server.entity.User;


@Path("/users")
@Component(provides={Object.class})
public class TestResource {
	
	@ServiceDependency
    private volatile TestService testService;

	private @Context HttpServletRequest request;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getList(@HeaderParam("Range") String range, @HeaderParam("content") String query) {
        QueryParams queryParams = QueryUtil.parserRequest(query, range);
        int count = testService.count(User.class, queryParams);
        String contentRange = String.format("items %d-%d/%d", queryParams.getFirst(), queryParams.getMax(), count);
        List<User> list = testService.query(User.class, queryParams).stream().collect(Collectors.toList());
        return Response.ok(list).header("Content-Range", contentRange).build();
    }
	
	@GET
	@Path("/{_key}")
	public Response get(@PathParam("_key") String _key) {
		try {
			QueryParams queryParams = new QueryParams();
			queryParams.getQuerys().add(QueryUtil.getQuery(Condition.EQUAL, "captch", _key));
			
			List<User> list = testService.query(User.class, queryParams).stream().collect(Collectors.toList());
			if(list.size() > 0){
				return Response.ok(list.get(0)).build();
			}
			return Response.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
    }
	
	
	@POST
	public Response save(User user){
		try {
			user = testService.persist(user);
			return Response.ok(user).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
}
