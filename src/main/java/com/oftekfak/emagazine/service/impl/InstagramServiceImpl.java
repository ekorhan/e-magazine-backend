package com.oftekfak.emagazine.service.impl;

import com.google.gson.Gson;
import com.oftekfak.emagazine.model.instagram.HashTagPostModel;
import com.oftekfak.emagazine.model.instagram.UserNameResponse.UserNameResponse;
import com.oftekfak.emagazine.service.IInstagramService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InstagramServiceImpl implements IInstagramService {

    @Override
    public List<String> findUsers(String hashTag) {
        long ms = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://z-p3.www.instagram.com/explore/tags/" + hashTag + "?__a=1";
        ResponseEntity<String> response
                = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, null, String.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Referer", "https://commentpicker.com/instagram-username.php");
        headersMap.put("Cookie", "ezoref_186623=google.com; ezoab_186623=mod1; ezCMPCCS=true; ezds=ffid%3D1%2Cw%3D1536%2Ch%3D864; ezohw=w%3D1496%2Ch%3D723; fontsLoaded=true; PHPSESSID=0objg8mu913hd16asa47ofjra4; _ga=GA1.2.1637982973.1625432963; _gid=GA1.2.2100571223.1625432963; SLG_GWPT_Show_Hide_tmp=1; SLG_wptGlobTipTmp=1; ezux_ifep_186623=true; ezoadgid_186623=-1; ezovid_186623=2094665756; lp_186623=https://commentpicker.com/instagram-user-id.php; ezovuuid_186623=542a18bd-237a-41b6-46f5-ea84b6b00e8a; __qca=P0-2115381707-1625435972370; active_template::186623=pub_site.1625436212; ezopvc_186623=2; ezepvv=763; ezovuuidtime_186623=1625436212; _gat=1; ezux_lpl_186623=1625436214251|9258e54a-8443-4b32-6941-8628fe3edb87|true; ezux_et_186623=191; ezux_tos_186623=2659");
        httpHeaders.setAll(headersMap);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseToken
                = restTemplate.exchange("https://commentpicker.com/actions/token.php",
                HttpMethod.GET,
                httpEntity,
                String.class);

        Gson gson = new Gson();

        HashTagPostModel hashTagPostModel = gson.fromJson(response.getBody(), HashTagPostModel.class);


        ArrayList<HashTagPostModel.Graphql.Hashtag.EdgeHashTagToMedia.Edges> list
                = hashTagPostModel.getGraphql().getHashtag().getEdgeHashTagToMedia().getEdges();

        ArrayList<String> responseList = new ArrayList<>();
        for (HashTagPostModel.Graphql.Hashtag.EdgeHashTagToMedia.Edges e : list) {
            if (responseList.size() == 3)
                return responseList;

            try {
                Long userId = e.getNode().getOwner().getId();
                if (userId == null) {
                    responseList.add(" NULL :  " + e.getNode().getDisplayUrl());
                    continue;
                }

                HttpEntity<String> httpEntity1 = new HttpEntity<>("body", httpHeaders);
                ResponseEntity<String> userNameRequest
                        = restTemplate.exchange(
                        String.format("https://commentpicker.com/actions/find-insta-username.php?userid=%s&token=%s",
                                userId, responseToken.getBody()),
                        HttpMethod.GET,
                        httpEntity1,
                        String.class);

                UserNameResponse userNameResponse = gson.fromJson(userNameRequest.getBody(), UserNameResponse.class);

                HttpHeaders httpHeaders1 = new HttpHeaders();
                HashMap<String, String> headersMap1 = new HashMap<>();
                headersMap.put("Referer", "https://www.instagram.com/");
                headersMap.put("Origin", "https://www.instagram.com");
                headersMap.put("X-csrftoken", "R7AGJIbcGIHidiTejvQDwuqJqnApJlsS");
                headersMap.put("x-ig-app-id", "936619743392459");
                headersMap.put("x-ig-www-claim", "hmac.AR0FSykm2pYK1gkoTqBCxX01N8tag1dUY5UJdWnOtI7pVNiE");
                headersMap.put("x-instagram-zero", "1");
                headersMap.put("x-asbd-id", "437806");
                headersMap.put("Cookie", "mid=YOI6FgALAAFsbcR_pNfAKIPNfO9E; ig_did=6AF26ACD-B192-4599-8981-B6FECE2807D8; ig_nrcb=1; csrftoken=Be1RPXBktKBv0sW0CgRw9uBg0Mq04q5c; ds_user_id=8573009150; sessionid=8573009150:e2YO6UKWHBfudS:10; shbid=2339; shbts=1625440933.601557; rur=RVA");
                httpHeaders1.setAll(headersMap1);

                ResponseEntity<String> instagramProfile
                        = restTemplate.exchange(
                        String.format("https://www.instagram.com/%s/", userNameResponse.getUserName()),
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaders1),
                        String.class);

                String profileHtmlFormat = instagramProfile.getBody();
                String neededContent = profileHtmlFormat.split("<meta content=")[1];
                neededContent = neededContent.split("/>")[0];
                neededContent = neededContent.split("-")[0];
                neededContent = neededContent.replace("\\", "");
                neededContent = neededContent.replace("\"", "");
                neededContent = neededContent.replace(",", "");
                System.out.println("neededContent: " + neededContent);
                long followersCount = 0;
                try {
                    followersCount = Long.parseLong(neededContent.split(" ")[0]);
                } catch (Exception ex1) {
                    System.out.println(neededContent.split(" ")[0] + " -|- " + ex1.getClass());
                }
                if (followersCount < 500)
                    continue;

                responseList.add("https://www.instagram.com/" +
                        userNameResponse.getUserName() +
                        " | " + neededContent);
            } catch (NullPointerException ignored) {
            }
        }

        long a = System.currentTimeMillis() - ms;

        System.out.println("a: " + a);
        return responseList;
    }
}
