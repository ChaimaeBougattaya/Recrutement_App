package gestion_annonces.controler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import gestion_annonces.model.bo.*;
import gestion_annonces.model.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet(name="MegaServlet",urlPatterns="*.do")
@MultipartConfig(maxFileSize = 56177216)//1.5mb
public class MegaServlet extends HttpServlet{
	Annonces annonces ;
	Candidatures candidatures;
	PrintStream out = new PrintStream(System.out);
	DAOoffre dao ;
	DAORecruteur daoR;
	DAOCandidat daoC;
	
	@Override
	public void init() throws ServletException {
		annonces = new Annonces();
		candidatures = new Candidatures();
		dao = new DAOoffre();
		daoR = new DAORecruteur();
		daoC = new DAOCandidat();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path =req.getServletPath();
		
		 if(path.contains("delete.do") && (req.getMethod().equals("GET")))
		{
			String username = req.getSession().getAttribute("userNOW").toString();
			Recruteur r = daoR.retrieveOne(username);
			long id = Long.parseLong(req.getParameter("id").toString());
			System.out.println("ici delete "+id);
			Offre o = new Offre();
			o.setId(id);
			o.setRecruteur(r);
			dao.delete(o);
			annonces.setOffres(r.getOffres());
			req.setAttribute("annonces", annonces);
			req.getRequestDispatcher("index.do").forward(req, resp);
			
		}else if (path.contains("updateoffre.do") && (req.getMethod().equals("GET")))
		{
			System.out.println("ici update le path "+path);
			String username = req.getSession().getAttribute("userNOW").toString();
			long id = Long.parseLong(req.getParameter("idoffre"));
			out.print("id = "+id);
			String titre = req.getParameter("titre");
			String description = req.getParameter("description");
			String profile = req.getParameter("profile");
			System.out.println("type : "+req.getParameter("type"));
			long type = Long.parseLong(req.getParameter("type"));
			
		
			Offre o = new Offre();
			Contrat c = new Contrat();
			Recruteur r = daoR.retrieveOne(username);
			c.setId(type);
			
			o.setId(id);
			o.setTitre(titre);
			o.setDescription(description);
			o.setProfile(profile);
			o.setDatepub(LocalDate.now().toString());
			o.setTypecontrat(c);
			o.setRecruteur(r);
			
		    System.out.println("le type : "+type);
			System.out.println(o);
			System.out.println("aller à update");
			dao.update(o);
			System.out.println("aprés update");

			annonces.setOffres(r.getOffres());
			req.setAttribute("annonces", annonces);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
		}else if(path.endsWith("insertoffre.do") && (req.getMethod().equals("POST")) )
		{
			String username = req.getSession().getAttribute("userNOW").toString();
			
			String titre = req.getParameter("titre");
			String description = req.getParameter("description");
			String profile = req.getParameter("profile");
			long type = Long.parseLong(req.getParameter("type"));
			
			Offre o = new Offre();
			Contrat c = new Contrat();
			Recruteur r = daoR.retrieveOne(username);
			c.setId(type);
			
			o.setTitre(titre);
			o.setDescription(description);
			o.setProfile(profile);
			o.setDatepub(LocalDate.now().toString());
			o.setTypecontrat(c);
			o.setRecruteur(r);
			
			System.out.println("le type : "+type);
			System.out.println(o);
			System.out.println("aller à create");
			dao.create(o);
			System.out.println("aprés à create");
		
			annonces.setOffres(r.getOffres());
			req.setAttribute("annonces", annonces);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			//resp.sendRedirect("index.jsp");
		}
		else if(path.contains("connecter.do")  && (req.getMethod().equals("POST")))
		{
			String username=req.getParameter("username");
			String pass=req.getParameter("password");
			
			if(username.length()!=0 && pass.length()!=0)
			{
				Recruteur r =daoR.retrieveOne(username,pass);
				if(r!=null)
				{
					System.out.println("le recruteur déja existe");
					req.getSession().setAttribute("userNOW", r.getUsername());
					annonces.setOffres(r.getOffres());
					req.setAttribute("annonces", annonces);
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				}
				else
				{
					System.out.println("le recruteur n'existe pas ");
					req.getRequestDispatcher("connecter.jsp").forward(req, resp);	
				}
			}else
			{
				System.out.println("remplir les inputs !! ");
				req.getRequestDispatcher("connecter.jsp").forward(req, resp);	
			}
		}
		else if (path.endsWith("deconnecter.do")  && (req.getMethod().equals("GET")))
		{
			req.getSession().removeAttribute("userNOW");
			req.getRequestDispatcher("Home.do").forward(req, resp);
		}
		else if(path.endsWith("Home.do") && (req.getMethod().equals("GET")))
		{
			System.out.println("iciiiiiiii home");
			System.out.println("la liste des offres --home.do--");
			
			List<Offre> listoffres = (List<Offre>) dao.retrieve();
			for(Offre oo : listoffres)
				System.out.println(oo);
			
			annonces.setOffres(listoffres);
			req.setAttribute("annonces", annonces);
			req.getRequestDispatcher("Home.jsp").forward(req, resp);
			
		}else if(path.endsWith("index.do") && (req.getMethod().equals("GET")))
		{
			System.out.println("iciiiiiiii index");
			System.out.println("la liste des offres --index.do--");
			
			
			if(req.getSession().getAttribute("userNOW")!=null)
			{
				String username=req.getSession().getAttribute("userNOW").toString();
				Recruteur r = daoR.retrieveOne(username);
				annonces.setOffres(r.getOffres());
				req.setAttribute("annonces", annonces);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else
			{
				req.getRequestDispatcher("connecter.jsp").forward(req, resp);
			}
		}else if (path.contains("update.do")  && (req.getMethod().equals("GET")))
		{
			long id = Long.parseLong(req.getParameter("id"));
			//List<Offre> listoffres = new ArrayList<Offre>();
			Offre of = dao.retrieveOne(id);
			//listoffres.add(of);
			req.setAttribute("of", of);
			req.getRequestDispatcher("updateOffre.jsp").forward(req, resp);
		}else if(path.endsWith("inscrire.do")  && (req.getMethod().equals("POST")))
		 {
			String nom = req.getParameter("nom");
			String prenom = req.getParameter("prenom");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println(nom + "  "+ prenom + "  "+ username + "  "+ password);
			
			if(nom.length()!=0 && prenom.length()!=0 && username.length()!=0 && password.length()!=0)
			{
				Recruteur r = new Recruteur();
					r.setNom(nom);
					r.setPrenom(prenom);
					r.setUsername(username);
					r.setPassword(password);
					
					boolean res = daoR.create(r);
					if(res)
					{
						r=daoR.retrieveOne(username);
						req.getSession().setAttribute("userNOW", username);
						annonces.setOffres(r.getOffres());
						req.setAttribute("annonces", annonces);
						System.out.println("recrteur ::: \n" +r);
						System.out.println("username in session = "+req.getSession().getAttribute("userNOW"));				
						req.getRequestDispatcher("index.jsp").forward(req, resp);
					}else
					{
						req.getRequestDispatcher("inscrire.jsp").forward(req, resp);
					}
				
			}else
			{
				req.getRequestDispatcher("inscrire.jsp").forward(req, resp);
			}
///////////////////////////////////////////////////////////////////////////////////////////////
		 }else if(path.contains("postuleroffre.do") &&(req.getMethod().equals("GET")))
		 {
			 if(req.getSession().getAttribute("CandidatNOW")!=null)
			 {
				 System.out.println("-------------------iciii-------------------");
				 int idoffre=Integer.parseInt(req.getParameter("idoffre"));
				 System.out.println("id = "+idoffre);
				 Offre o = dao.retrieveOne(idoffre);
				 System.out.println("offre ********************** "+o);
				 String username = req.getSession().getAttribute("CandidatNOW").toString();
				 Candidat c = daoC.retrieveOne(username);
				 System.out.println("candidat ********************* "+c);
				 Set<Offre> off = c.getCondidatures();
				 off.add(o);
				 c.setCondidatures(off);
				 
				 Set<Candidat> can=null;
				 if(o.getCandidats()!=null)
					 can=o.getCandidats();
				 else
					 can = new TreeSet<Candidat>();
				 
				 can.add(c);
				 o.setCandidats(can);
				 
				 dao.update(o);
				 daoC.update(c);
				
				 req.getRequestDispatcher("HomeCandidat.do").forward(req, resp);
			 }else
				 req.getRequestDispatcher("connecterCandidat.jsp").forward(req, resp);
			 
		 }
		else if(path.contains("inscrireCandidat.do") && (req.getMethod().equals("POST")))
		{
			String nom = req.getParameter("nom");
			String prenom = req.getParameter("prenom");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println(nom + "  "+ prenom + "  "+ username + "  "+ password);
			
			if(nom.length()!=0 && prenom.length()!=0 && username.length()!=0 && password.length()!=0)
			{
				Candidat c = new Candidat();
				c.setNom(nom);
				c.setPrenom(prenom);
				c.setPassword(password);
				c.setUsername(username);
				
				Part part = null;
				InputStream is=null;
				part = req.getPart("cv");
				if (part != null) {
		            try {
		               is = part.getInputStream();
		               byte[] cvbytes = IOUtils. toByteArray(is);
		               c.setCv(cvbytes);
		            } catch (Exception e) {
		                out.println(e);
		            }
		        }
				part = req.getPart("lm");
				if (part != null) {
		            try {
		               is = part.getInputStream();
		               byte[] lmbytes = IOUtils. toByteArray(is);
		               c.setLettreM(lmbytes);
		            } catch (Exception e) {
		                out.println(e);
		            }
		        }
				
				boolean res = daoC.create(c);
				if(res)
				{
					c=daoC.retrieveOne(username);
					req.getSession().setAttribute("CandidatNOW",username);
					req.setAttribute("condidatures", c.getCondidatures());
					req.getRequestDispatcher("indexCandidat.jsp").forward(req, resp);
				}else
				{
					req.getRequestDispatcher("inscrireCandidat.jsp").forward(req, resp);
				}
				
			}else
			{
				req.getRequestDispatcher("inscrireCandidat.jsp").forward(req, resp);	
			}
			
		}else if(path.contains("HomeCandidat.do") && (req.getMethod().equals("GET")))
		{
			System.out.println("iciiiiiiii HomeCandidat.do");
			System.out.println("la liste des offres -- HomeCandidat.do--");
			
			List<Offre> listoffres = (List<Offre>) dao.retrieve();
			for(Offre oo : listoffres)
				System.out.println(oo);
			
			annonces.setOffres(listoffres);
			req.setAttribute("annonces", annonces);
			req.getRequestDispatcher("HomeCandidat.jsp").forward(req, resp);
			
		}else if(path.contains("indexCandidat.do") && (req.getMethod().equals("GET")))
		{
			System.out.println("iciiiiiiii indexCandidat.do");
			System.out.println("la liste des offres -- indexCandidat.do--");
			if(req.getSession().getAttribute("CandidatNOW")!=null)
			{
				Candidat c = daoC.retrieveOne(req.getSession().getAttribute("CandidatNOW").toString());
				annonces.setOffres(c.getCondidatures());
				req.setAttribute("annonces", annonces);
				req.getRequestDispatcher("indexCandidat.jsp").forward(req, resp);
			}else
			{
				req.getRequestDispatcher("connecterCandidat.jsp").forward(req, resp);
			}
			
		}else if(path.contains("connecterCandidat.do") && (req.getMethod().equals("POST")))
		{
			String username=req.getParameter("username");
			String pass=req.getParameter("password");
			
			if(username.length()!=0 && pass.length()!=0)
			{
				Candidat c = daoC.retrieveOne(username,pass);
				if(c!=null)
				{
					System.out.println("le candidat déja existe");
					req.getSession().setAttribute("CandidatNOW", c.getUsername());
					annonces.setOffres(c.getCondidatures());
					req.setAttribute("annonces", annonces);
					req.getRequestDispatcher("indexCandidat.jsp").forward(req, resp);
				}
				else
				{
					System.out.println("le candidat n'existe pas ");
					req.getRequestDispatcher("connecterCondidat.jsp").forward(req, resp);	
				}
			}else
			{
				System.out.println("remplir les inputs !! ");
				req.getRequestDispatcher("connecterCondidat.jsp").forward(req, resp);	
			}
		}else if(path.endsWith("deconnecterCandidat.do") && (req.getMethod().equals("GET")))
		{
			req.getSession().removeAttribute("CandidatNOW");
			req.getRequestDispatcher("HomeCandidat.do").forward(req, resp);
			
		}else if(path.contains("detail.do") && (req.getMethod().equals("GET")) )
		{
			
			long id = Long.parseLong(req.getParameter("id"));
			System.out.println("id = "+id);
			Offre o = dao.retrieveOne(id);
			
			for(Candidat c : o.getCandidats())
				System.out.println("candidat =====>  "+c);
			
			candidatures.setCandidats(o.getCandidats());
			
			//System.out.println("offre =>     =>  "+o);
			req.setAttribute("candidatures", candidatures);
			req.getRequestDispatcher("detailoffre.jsp").forward(req, resp);
			
		}else if(path.contains("dowloadCV.do") && (req.getMethod().equals("GET")))
		{
			long id = Long.parseLong(req.getParameter("id"));
			Candidat c = daoC.retrieveOne(id);
			byte[]content=c.getCv();
			
			OutputStream out = new FileOutputStream(c.getUsername()+"_CV.pdf");
			out.write(c.getCv()); 
			out.close();
			
			resp.setContentType("application/force-download");
			resp.setContentLength(content.length);
			   //response.setHeader("Content-Transfer-Encoding", "binary");
			   //String filename = URLEncoder.encode(pieceJointe.getLbnompcj(), "UTF-8");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + c.getUsername()+"_CV.pdf" + "\"");

			resp.getOutputStream().write(content);
			resp.getOutputStream().close();
			//resp.sendRedirect("offresPostules.php");
			
			req.getRequestDispatcher("detailoffre.do?id="+id).forward(req, resp);
			
		}else if(path.contains("dowloadLM.do") && (req.getMethod().equals("GET")))
		{
			long id = Long.parseLong(req.getParameter("id"));
			Candidat c = daoC.retrieveOne(id);
			byte[]content=c.getLettreM();
			
			OutputStream out = new FileOutputStream(c.getUsername()+"_LM.pdf");
			out.write(c.getLettreM()); 
			out.close();
			
			resp.setContentType("application/force-download");
			resp.setContentLength(content.length);
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + c.getUsername()+"_LM.pdf" + "\"");

			resp.getOutputStream().write(content);
			resp.getOutputStream().close();
			
			req.getRequestDispatcher("detailoffre.do?id="+id).forward(req, resp);
			
		}else if(path.endsWith("updatecv.do") && (req.getMethod().equals("POST")))
		{
			if(req.getSession().getAttribute("CandidatNOW")!=null)
			{
				String username = req.getSession().getAttribute("CandidatNOW").toString();
				Candidat c = daoC.retrieveOne(username);
				Part part = null;
				InputStream is=null;
				part = req.getPart("cv");
				if (part != null) {
		            try {
		               is = part.getInputStream();
		               byte[] cvbytes = IOUtils. toByteArray(is);
		               c.setCv(cvbytes);
		            } catch (Exception e) {
		                out.println(e);
		            }
		        }
				daoC.update(c);
				req.getRequestDispatcher("indexCandidat.jsp").forward(req, resp);
			}
			else
				req.getRequestDispatcher("indexCandidat.do").forward(req, resp);
		}else
			{
				resp.sendError(Response.SC_NOT_FOUND);
			}	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void destroy() {
		
	}
}
