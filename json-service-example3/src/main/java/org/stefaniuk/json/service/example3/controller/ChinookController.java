package org.stefaniuk.json.service.example3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stefaniuk.json.service.example3.model.Artist;
import org.stefaniuk.json.service.example3.model.ArtistDao;
import org.stefaniuk.json.service.example3.service.AlbumService;
import org.stefaniuk.json.service.example3.service.ArtistService;
import org.stefaniuk.json.service.example3.service.TrackService;

@Controller
@RequestMapping(value = "/chinook/*")
public class ChinookController extends AbstractController {

    @Autowired
    private ArtistDao artistDao;

    @Override
    @RequestMapping("service/{service}")
    public ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String service) throws Exception {

        ResponseEntity<String> re = null;

        if(service.equals("artist")) {
            re = handleJsonRpc(request, response, ArtistService.class);
        }
        else if(service.equals("album")) {
            re = handleJsonRpc(request, response, AlbumService.class);
        }
        else if(service.equals("track")) {
            re = handleJsonRpc(request, response, TrackService.class);
        }

        List<Artist> list = artistDao.findAll();
        for(Artist artist: list) {
            System.out.println(artist);
        }

        //File file = new File(session.getServletContext().getRealPath("/WEB-INF/classes") + "/chinook.sqlite");
        //SqlJetDb db = SqlJetDb.open(file, true);

        return re;
    }

}
